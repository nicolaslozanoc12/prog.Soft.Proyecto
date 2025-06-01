package com.software1.services;

import com.software1.models.Bodega;
import com.software1.repositories.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BodegaService {
    private final BodegaRepository bodegaRepository;

    @Autowired
    public BodegaService(BodegaRepository bodegaRepository) {
        this.bodegaRepository = bodegaRepository;
    }

    public Bodega actualizarNumeroDisponible(Long idPieza, Integer cantidadLlegada) {
        // Buscar la pieza en la base de datos
        Optional<Bodega> bodegaOptional = bodegaRepository.findById(idPieza);

        if (bodegaOptional.isPresent()) {
            Bodega bodega = bodegaOptional.get();
            // Sumar la cantidad llegada al número disponible actual
            bodega.setNumeroDisponible(bodega.getNumeroDisponible() + cantidadLlegada);
            // Guardar los cambios en la base de datos
            return bodegaRepository.save(bodega);
        } else {
            throw new RuntimeException("La pieza con ID " + idPieza + " no existe.");
        }
    }
    public Bodega restarNumeroDisponible(Long idPieza, int cantidadARestar) {
        // Buscar la pieza en la base de datos
        Optional<Bodega> bodegaOptional = bodegaRepository.findById(idPieza);

        if (bodegaOptional.isPresent()) {
            Bodega bodega = bodegaOptional.get();
            // Verificar que haya suficiente cantidad disponible
            if (bodega.getNumeroDisponible() < cantidadARestar) {
                throw new RuntimeException("No hay suficiente cantidad disponible en la bodega.");
            }
            // Restar la cantidad
            bodega.setNumeroDisponible(bodega.getNumeroDisponible() - cantidadARestar);
            // Guardar los cambios en la base de datos
            return bodegaRepository.save(bodega);
        } else {
            throw new RuntimeException("La pieza con ID " + idPieza + " no existe.");
        }
    }

}
