package com.software1.messages;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class EnviarSolicitudDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("📤 Enviando solicitud de insumos al proveedor...");
        // Simulación de un insumo
        Long idLinea = ((Number) execution.getVariable("idPieza")).longValue();
        System.out.println("Id del Insumo solicitado: " + idLinea);
    }
}