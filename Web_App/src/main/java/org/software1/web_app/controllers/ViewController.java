package org.software1.web_app.controllers;

import org.software1.web_app.services.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // --- Endpoints para manejar formularios de TAREAS ---

    // Muestra el formulario para una tarea específica
    @GetMapping("/tarea/{taskId}/form")
    public String showTaskForm(@PathVariable String taskId, Model model) {
        // 1. Obtenemos los detalles de la tarea desde Camunda
        Map<String, Object> taskDetails = camundaService.getTaskDetails(taskId);

        // 2. Extraemos la Form Key que definimos en el Modeler
        String formKey = (String) taskDetails.get("formKey");

        // Si no hay una form key, mostramos una página genérica o de error
        if (formKey == null || formKey.isEmpty()) {
            model.addAttribute("error", "La tarea no tiene un formulario asociado.");
            return "error-page"; // Debes crear esta página de error
        }

        model.addAttribute("taskId", taskId);
        model.addAttribute("taskDetails", taskDetails);

        // 3. Devolvemos la vista que coincide con el nombre de la Form Key
        // Asumimos que los formularios estarán en una carpeta "forms/"
        return "forms/" + formKey;
    }

    // Procesa el envío del formulario de la tarea
    @PostMapping("/tarea/{taskId}/completar")
    public String completeTask(@PathVariable String taskId, @RequestParam Map<String,String> allParams, Model model) {
        // Aquí necesitarías convertir 'allParams' al formato que Camunda espera
        // Por simplicidad, lo omitimos, pero la lógica iría aquí.

        // camundaService.completeTask(taskId, variables);

        model.addAttribute("mensaje", "Tarea " + taskId + " completada (simulación).");
        return "resultado";
    }
}
