package com.software1.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "provedor")
public class Provedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprovedor")
    private Long idProvedor;

    @Column(name = "nombre", length = 100, nullable  = false) // VARCHAR(100), no nulo
    private String nombre;

    @Column(name = "correo", length = 100, unique = true) // VARCHAR(100), único
    private String correo;

    // Relación OneToMany con Almacen (un proveedor puede tener muchas piezas)
    // 'mappedBy = "proveedor"' indica que la entidad Almacen es la dueña de la relación
    // y tiene un campo llamado 'proveedor' que mapea esta relación.
    @OneToMany(mappedBy = "provedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Bodega> piezasSuministradas;

    // Constructor vacío necesario para JPA
    public Provedor() {
    }

    public Provedor(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;

    }

    public Long getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(Long idProvedor) {
        this.idProvedor = idProvedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Bodega> getPiezasSuministradas() {
        return piezasSuministradas;
    }

    public void setPiezasSuministradas(Set<Bodega> piezasSuministradas) {
        this.piezasSuministradas = piezasSuministradas;
    }
}
