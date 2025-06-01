package com.software1.services;

import com.software1.models.IncidenteCalidad;
import com.software1.repositories.IncidenteCalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class IncidenteCalidadService {
    private final IncidenteCalidadRepository incidenteCalidadRepository;

    @Autowired
    public IncidenteCalidadService(IncidenteCalidadRepository incidenteCalidadRepository) {
        this.incidenteCalidadRepository = incidenteCalidadRepository;
    }

    public void guardarIncidente(IncidenteCalidad incidente) {
        if (incidente.getHoraFinal() == null) {
            incidente.setHoraFinal(OffsetDateTime.now()); // Establecer hora de alerta si no se provee
        }
        if (incidente.getIdLinea() == null) {
            throw new IllegalArgumentException("El campo id_linea no puede ser nulo.");
        }
        incidenteCalidadRepository.save(incidente);
    }


}
