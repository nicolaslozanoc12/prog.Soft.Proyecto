package com.software1.controlCalidad;

import com.software1.services.EstadoLineaService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReanudarLinea {
    private final EstadoLineaService estadoLineaService;


    @Autowired
    public ReanudarLinea(EstadoLineaService estadoLineaService) {
        this.estadoLineaService = estadoLineaService;
    }
    public void execute(DelegateExecution execution) {
        Long idLinea = (Long) execution.getVariable("idLinea");
        boolean encendido = false;
        estadoLineaService.turnOffEstadoLinea(idLinea, false);

    }
}
