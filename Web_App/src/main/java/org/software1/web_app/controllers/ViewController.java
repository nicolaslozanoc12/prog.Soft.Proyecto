package org.software1.web_app.controllers;

import org.software1.web_app.services.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    @Controller
    public class ViewController {
        @Autowired
        private CamundaService camundaService;


        // 1. Muestra la página de inicio (lista de procesos)
        @GetMapping("/")
        public String index() {
            return "index";
        }

        // 2. Muestra la página para seleccionar un rol
        @GetMapping("/proceso/{processKey}/roles")
        public String selectRole(@PathVariable String processKey, Model model) {
            model.addAttribute("processKey", processKey);
            // Aquí podrías pasar una lista de roles si la tuvieras
            return "roles";
        }

        // 3. Muestra el "dashboard" de un rol: acciones y tareas pendientes
        @GetMapping("/rol/{groupName}/dashboard")
        public String roleDashboard(@PathVariable String groupName, @RequestParam String processKey, Model model) {
            // Buscamos las tareas pendientes para este grupo
            List<Map<String, Object>> tasks = camundaService.getTasksForGroup(groupName);

            model.addAttribute("groupName", groupName);
            model.addAttribute("processKey", processKey);
            model.addAttribute("tasks", tasks);

            return "dashboard-rol";
        }

        @GetMapping("/proceso/{processKey}/iniciar")
        public String iniciarProcesoYRedirigir(@PathVariable String processKey, @RequestParam String groupName) {
            // 1. Inicia el proceso sin variables iniciales. Esto es correcto.
            // Camunda creará la instancia y la primera tarea de usuario "Alerta".
            camundaService.iniciarProceso(processKey, java.util.Collections.emptyMap());

            // 2. Redirige de vuelta al dashboard del rol del usuario.
            // La página se recargará y la nueva tarea "Alerta" aparecerá en la lista.
            return "redirect:/rol/" + groupName + "/dashboard?processKey=" + processKey;
        }

        @PostMapping("/proceso/{processKey}/iniciar")
        public String handleStartForm(@PathVariable String processKey,
                                      @RequestParam Long idLinea,
                                      @RequestParam String descripcionInicial,
                                      Model model) {

            Map<String, Object> variables = new HashMap<>();
            variables.put("idLinea", Map.of("value", idLinea));
            variables.put("descripcionInicial", Map.of("value", descripcionInicial));

            camundaService.iniciarProceso(processKey, variables);

            model.addAttribute("mensaje", "Proceso " + processKey + " iniciado correctamente.");
            return "resultado";
        }

        // --- Endpoints para manejar formularios de TAREAS ---

        // Muestra el formulario para una tarea específica
        @GetMapping("/tarea/{taskId}/form")
        public String showTaskForm(@PathVariable String taskId, Model model,
                                   @RequestParam String groupName, @RequestParam String processKey) {
            // 1. Obtenemos los detalles de la tarea (como antes)
            Map<String, Object> taskDetails = camundaService.getTaskDetails(taskId);
            String formKey = (String) taskDetails.get("formKey");

            if (formKey == null || formKey.isEmpty()) {
                model.addAttribute("error", "La tarea no tiene un formulario asociado.");
                return "error-page";
            }

            // 2. ¡NUEVO! Obtenemos las variables de la tarea
            Map<String, Object> variables = camundaService.getTaskVariables(taskId);

            // 3. Pasamos todo a la vista del formulario
            model.addAttribute("taskId", taskId);
            model.addAttribute("taskDetails", taskDetails);
            model.addAttribute("groupName", groupName);
            model.addAttribute("processKey", processKey);
            model.addAttribute("variables", variables); // <-- Pasamos las variables

            // 4. Devolvemos la vista que coincide con el nombre de la Form Key
            return "forms/" + formKey;
        }

        @PostMapping("/tarea/{taskId}/completar")
        public String completeGenericTask(@PathVariable String taskId,
                                          @RequestParam Map<String, String> formVariables) {

            // Extraemos los datos de redirección
            String groupName = formVariables.remove("groupName");
            String processKey = formVariables.remove("processKey");

            Map<String, Object> camundaVariables = new HashMap<>();

            // Iteramos sobre el resto de las variables del formulario
            for (Map.Entry<String, String> entry : formVariables.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Object convertedValue = value; // Por defecto, es un String

                // --- INICIO DE LA NUEVA LÓGICA INTELIGENTE ---
                try {
                    // Intenta convertir el valor a un Long
                    convertedValue = Long.parseLong(value);
                } catch (NumberFormatException e1) {
                    // Si falla, intenta convertirlo a un Boolean (para los gateways)
                    if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                        convertedValue = Boolean.parseBoolean(value);
                    }
                    // Si todo falla, se queda como el String original
                }
                // --- FIN DE LA NUEVA LÓGICA ---

                // Añadimos la variable al mapa con su tipo correcto
                camundaVariables.put(key, Map.of("value", convertedValue));
            }

            // Llamamos al servicio para completar la tarea
            camundaService.completeTask(taskId, camundaVariables);

            // Redirigimos al dashboard
            return "redirect:/rol/" + groupName + "/dashboard?processKey=" + processKey;
        }


    }
