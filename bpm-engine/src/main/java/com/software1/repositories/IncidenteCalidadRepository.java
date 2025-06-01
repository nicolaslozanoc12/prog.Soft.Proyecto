package com.software1.repositories;

import com.software1.controlCalidad.RegistroIncidente;
import com.software1.models.IncidenteCalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteCalidadRepository extends JpaRepository<IncidenteCalidad, Long> {

}
