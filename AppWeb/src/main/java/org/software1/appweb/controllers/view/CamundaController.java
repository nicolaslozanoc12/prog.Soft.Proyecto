package org.software1.appweb.controllers.view;

import org.software1.appweb.services.CamundaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CamundaController {

    private final CamundaService camundaService;

    public CamundaController(CamundaService camundaService) {
        this.camundaService = camundaService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", camundaService.getTasks());
        return "index";
    }

    /*@PostMapping("/iniciar")
    public String iniciar() {
        Map<String, Object> variables = Map.of("solicitante", "cliente1");
        return camundaService.iniciarProceso("ProcesoGestionInsumos", variables);
    }*/

    @GetMapping("/task/{id}")
    public String showForm(@PathVariable String id, Model model) {
        Map<String, Object> formVariables = camundaService.getFormVariables(id);
        Map<String, Object> taskInfo = camundaService.getTaskInfo(id);

        model.addAttribute("taskId", id);
        model.addAttribute("formVariables", formVariables);
        model.addAttribute("taskInfo", taskInfo);

        return "formulario-tipo";
    }

    @PostMapping("/task/{id}/complete")
    public String complete(@PathVariable String id, @RequestParam Map<String, String> formData) {
        camundaService.completarTarea(id, new HashMap<>(formData));
        return "redirect:/";
    }
}
