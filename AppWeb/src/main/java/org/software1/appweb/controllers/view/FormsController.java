package org.software1.appweb.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class FormsController {

    @GetMapping("/{taskId}")
    public String showForm(@PathVariable String taskId, Model model) {
        // Llama al API del backend Camunda para obtener datos
        model.addAttribute("taskId", taskId);
        return "formulario-tipo"; // Thymeleaf + Bootstrap
    }

    @PostMapping("/{taskId}")
    public String submitForm(@PathVariable String taskId, @RequestParam Map<String, String> data) {
        // Enviar datos al API Camunda
        return "redirect:/tareas";
    }
    @GetMapping("/form/hallazgo")
    public String hallazgoForm(Model model) {
        return "formularioHallazgoAnomalia";
    }

    @GetMapping("/form/inventario")
    public String inventarioForm(Model model) {
        return "reporteInventario";
    }

    @GetMapping("/form/aviso")
    public String avisoForm(Model model) {
        return "avisoSupervisor";
    }
}
