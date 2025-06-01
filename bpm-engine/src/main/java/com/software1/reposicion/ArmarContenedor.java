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
        Long idLinea = (Long) execution.getVariable("idLinea");
        Long idEstanteria = (Long) execution.getVariable("idEstanteria");
        Long idPieza = (Long) execution.getVariable("idPieza");
        Long maxPiezas = (Long) execution.getVariable("maxPiezas");
        Long piezasActuales = (Long) execution.getVariable("piezasActuales");

        int piezasFaltantes = Math.toIntExact(maxPiezas) - Math.toIntExact(piezasActuales);

        bodegaService.restarNumeroDisponible(idPieza, piezasFaltantes);

        System.out.println("insumos completados en el contenedor: " + idLinea + " con estanteria: " + idEstanteria + " y pieza: " + idPieza);
    }
}
