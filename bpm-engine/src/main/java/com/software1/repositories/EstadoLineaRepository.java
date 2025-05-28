package com.software1.repositories;

import com.software1.models.EstadoLinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoLineaRepository  extends JpaRepository<EstadoLinea, Long> {

}
