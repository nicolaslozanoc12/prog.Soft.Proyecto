package com.software1.reposicion;

import com.software1.services.BodegaService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecibirInsumos implements JavaDelegate {
    @Autowired
    private BodegaService  bodegaService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long idLinea = ((Number) execution.getVariable("idPieza")).longValue();
        Long cantidadLlegadaLong = (Long) execution.getVariable("cantidadLlegada");
        bodegaService.actualizarNumeroDisponible(idLinea, Math.toIntExact(cantidadLlegadaLong));
        System.out.println("Insumos recibidos correctamente.");
    }


}
