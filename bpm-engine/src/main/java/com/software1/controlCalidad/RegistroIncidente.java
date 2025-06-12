package com.software1.controlCalidad;

import com.software1.models.IncidenteCalidad;
import com.software1.services.IncidenteCalidadService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistroIncidente  implements JavaDelegate {

    @Autowired
    private IncidenteCalidadService incidenteCalidadService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long idLinea = ((Number) execution.getVariable("idLinea")).longValue();
        System.out.printf("Id de la línea: %d%n", idLinea);
        String horaAlerta = (String) execution.getVariable("horaAlerta");
        String  causaRaiz = (String) execution.getVariable("causaRaiz");
        String accionCorrectiva  = (String) execution.getVariable("accionCorrectiva");
        Boolean calidad = (Boolean) execution.getVariable("calidad");


        IncidenteCalidad incidentecalidad = new IncidenteCalidad(idLinea, horaAlerta, causaRaiz, accionCorrectiva,  calidad);

        incidenteCalidadService.guardarIncidente(incidentecalidad);
    }


}
