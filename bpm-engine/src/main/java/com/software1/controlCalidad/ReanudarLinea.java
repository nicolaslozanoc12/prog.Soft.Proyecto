package com.software1.controlCalidad;

import com.software1.services.EstadoLineaService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReanudarLinea implements JavaDelegate {
    private final EstadoLineaService estadoLineaService;


    @Autowired
    public ReanudarLinea(EstadoLineaService estadoLineaService) {
        this.estadoLineaService = estadoLineaService;
    }
    @Override
    public void execute(DelegateExecution execution) {
        Long idLinea = ((Number) execution.getVariable("idLinea")).longValue();
        estadoLineaService.modifyEstadoLinea(idLinea, false);

    }
}
