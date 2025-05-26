package com.software1.services;

import com.software1.models.EstadoLinea;
import com.software1.repositories.EstadoLineaRepository;

public class EstadoLineaService {
    private final EstadoLineaRepository estadoLineaRepository;


    public EstadoLineaService(EstadoLineaRepository estadoLineaRepository) {
        this.estadoLineaRepository = estadoLineaRepository;
    }

    public EstadoLinea updateEncendidoEstadoLinea(Long id, Boolean encendido) {
        return estadoLineaRepository.findById(id)
                .map(estadoLinea -> {
                    estadoLinea.setEncendido(encendido);
                    return estadoLineaRepository.save(estadoLinea);
                })
                .orElse(null);
    }
}
