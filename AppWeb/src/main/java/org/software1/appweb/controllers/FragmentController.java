package org.software1.appweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/fragment/formulario-anomalia")
    public String loadFormularioAnomalia() {
        return "fragment/formAnomalia :: formAnomalia";
    }

    @GetMapping("/fragment/control-calidad")
    public String loadControlCalidad() {
        return "fragment/controlCalidad :: controlCalidad";
    }

    @GetMapping("/fragment/reporte-inventario")
    public String loadReporteInventario() {
        return "fragment/reporteInventario :: reporteInventario";
    }

}
