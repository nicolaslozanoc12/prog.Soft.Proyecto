package com.software1.services;

import com.software1.models.EstadoLinea;
import com.software1.repositories.EstadoLineaRepository;
import org.springframework.stereotype.Service;

@Service
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
    public EstadoLinea turnOffEstadoLinea(Long id, Boolean encendido) {
        return estadoLineaRepository.findById(id)
                .map(estadoLinea -> {
                    if (estadoLinea.getEncendido() != null && estadoLinea.getEncendido()) {
                        estadoLinea.setEncendido(encendido); // Cambia el estado a apagado
                        System.out.println("Cambiando estado de la línea " + estadoLinea.getNombre() + " (ID: " + estadoLinea.getIdLinea() + ") a APAGADO.");
                        return estadoLineaRepository.save(estadoLinea);
                    } else {
                        estadoLinea.setEncendido(encendido); // Cambia el estado a apagado
                        System.out.println("Cambiando estado de la línea " + estadoLinea.getNombre() + " (ID: " + estadoLinea.getIdLinea() + ") a ENCENDIDO.");
                        return estadoLineaRepository.save(estadoLinea);
                    }

                })
                .orElseGet(() -> {
                    System.out.println("No se encontró la línea con ID: " + id);
                    return null; // No se encontró la línea
                });
    }

}
