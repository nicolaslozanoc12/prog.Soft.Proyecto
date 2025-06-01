package com.software1.repositories;

import com.software1.models.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository  extends JpaRepository<Bodega, Long> {
}
