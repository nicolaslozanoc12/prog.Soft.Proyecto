package com.software1.models;

import com.software1.repositories.EstadoLineaRepository;
import jakarta.persistence.*;

@Entity
@Table (name = "estadolinea")
public class EstadoLinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLinea;
    private String nombre;
    private Boolean encendido;

    // Constructor vacío
    public EstadoLinea() {
    }

    // Constructor con todos los campos
    public EstadoLinea(String nombre, Boolean encendido) {
        this.nombre = nombre;
        this.encendido = encendido;
    }

    // Getters y Setters para todos los campos

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEncendido() {
        return encendido;
    }

    public void setEncendido(Boolean encendido) {
        this.encendido = encendido;
    }

    @Override
    public String toString() {
        return "EstadoLineaService{" +
                "idLinea=" + idLinea +
                ", nombre='" + nombre + '\'' +
                ", encendido=" + encendido +
                '}';
    }

}
