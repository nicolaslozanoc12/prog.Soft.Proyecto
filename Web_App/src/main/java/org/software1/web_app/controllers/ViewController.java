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
            // 1. Pide los detalles de la tarea a Camunda
            Map<String, Object> taskDetails = camundaService.getTaskDetails(taskId);
            String formKey = (String) taskDetails.get("formKey");

            // 2. Intenta obtener la "Form Key" de la tarea

            Map<String, Object> variables = camundaService.getTaskVariables(taskId);

            model.addAttribute("taskId", taskId);
            model.addAttribute("taskDetails", taskDetails);
            model.addAttribute("groupName", groupName);
            model.addAttribute("processKey", processKey);
            model.addAttribute("variables", variables); // <-- Pasamos las variables

            // 3. ¡AQUÍ ESTÁ LA CLAVE!
            // Si la tarea en Camunda NO TIENE una "Form Key" definida,
            // formKey será nulo y el código intentará mostrar la página de error.
            if (formKey == null || formKey.isEmpty()) {
                model.addAttribute("error", "La tarea no tiene un formulario asociado.");
                return "error-page"; // <-- Esto es lo que causa tu error
            }

            // Si todo va bien, devuelve el formulario correcto
            return "forms/" + formKey;
        }

        // Procesa el envío del formulario de la tarea
        @PostMapping("/tarea/{taskId}/completar")
        public String completeTask(@PathVariable String taskId,
                                   @RequestParam String horaAlerta,
                                   @RequestParam Long idLinea,
                                   @RequestParam String groupName,
                                   @RequestParam String processKey) {

            // 1. Preparamos las variables del formulario para enviarlas a Camunda.
            // Estos datos se añadirán al contexto del proceso.
            Map<String, Object> variables = new HashMap<>();
            variables.put("horaAlerta", Map.of("value", horaAlerta));
            variables.put("idLinea", Map.of("value", idLinea));

            // 2. Llamamos al servicio para completar la tarea con sus variables.
            // Camunda ahora avanzará a la siguiente tarea (la que ejecuta RegistroIncidente.java).
            camundaService.completeTask(taskId, variables);

            // 3. Redirigimos al dashboard para que el usuario vea la lista de tareas actualizada.
            return "redirect:/rol/" + groupName + "/dashboard?processKey=" + processKey;
        }


    }
