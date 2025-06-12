package org.software1.appweb.controllers;


import org.software1.appweb.services.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/*
@Controller
public class ProcesoController {

    @Autowired
    private CamundaService camundaService;

    @GetMapping("/iniciar-proceso")
    public String iniciarProceso(Model model) {
        String resultado = camundaService.iniciarProceso("Control Calidad"); // usa el ID de tu BPMN
        model.addAttribute("mensaje", resultado);
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Página de inicio
    }
}
*/