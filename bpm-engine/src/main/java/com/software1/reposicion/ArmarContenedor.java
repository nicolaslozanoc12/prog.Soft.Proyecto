package com.software1.reposicion;

import com.software1.services.BodegaService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmarContenedor  implements JavaDelegate {

    @Autowired
    private BodegaService bodegaService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long idLinea = ((Number) execution.getVariable("idLinea")).longValue();
        Long idEstanteria = ((Number) execution.getVariable("idEstanteria")).longValue();
        Long idPieza = ((Number) execution.getVariable("idPieza")).longValue();
        Long maxPiezas = ((Number) execution.getVariable("maxPiezas")).longValue();
        Long piezasActuales = ((Number) execution.getVariable("piezasActuales")).longValue();

        int piezasFaltantes = Math.toIntExact(maxPiezas) - Math.toIntExact(piezasActuales);

        bodegaService.restarNumeroDisponible(idPieza, piezasFaltantes);

        System.out.println("insumos completados en el contenedor: " + idLinea + " con estanteria: " + idEstanteria + " y pieza: " + idPieza);
    }
}
