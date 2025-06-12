package com.software1.messages;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class RecibirInsumosDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("📦 Recibiendo insumos del proveedor...");
        execution.setVariable("insumos_recibidos", true);
        System.out.println("✔ Insumos recibidos correctamente.");
    }
}