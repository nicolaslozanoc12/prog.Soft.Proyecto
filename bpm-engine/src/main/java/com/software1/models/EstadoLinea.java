package com.software1.models;

import com.software1.repositories.EstadoLineaRepository;
import jakarta.persistence.*;

@Entity
@Table (name = "estadolinea")
public class EstadoLinea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLinea;
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    @Column(name = "contador")
    private int contador;
    @Column(name = "encendido")
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

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
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

    public int getContador() {
        return contador;
    }
    public void setContador(int contador) {
        this.contador = contador;
    }

}
