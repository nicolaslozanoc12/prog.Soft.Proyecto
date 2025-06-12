package org.software1.appweb.controllers;

import org.software1.appweb.services.CamundaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FragmentController {

    private final CamundaService camundaService;

    public FragmentController(CamundaService camundaService) {
        this.camundaService = camundaService;
    }

    @GetMapping("/fragment/formulario-anomalia")
    public String loadFormularioAnomalia() {
        return "fragment/formAnomalia :: formAnomalia";
    }

    @GetMapping("/fragment/formCalidad")
    public String loadControlCalidad() {
        return "fragment/formCalidad :: formCalidad";
    }

    @GetMapping("/fragment/reporte-inventario")
    public String loadReporteInventario() {
        return "fragment/reporteInventario :: reporteInventario";
    }
    @GetMapping("/fragment/crearProceso")
    public String loadCrearProceso() {
        return "fragment/crearProceso :: crearProceso";
    }
    @GetMapping("/fragment/procesos")
    public String loadMostrarProcesos(Model model) {
        model.addAttribute("tasks", camundaService.getTasks());
        return "fragment/procesos :: procesos";
    }
    @PostMapping("/process/start")
    public String startProcess(@RequestParam String processKey) {
        camundaService.iniciarProceso(processKey);
        return "redirect:/"; // o redirige a otra vista si prefieres
    }



}
