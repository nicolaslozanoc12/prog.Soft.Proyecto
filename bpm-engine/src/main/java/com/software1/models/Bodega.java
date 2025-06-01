package com.software1.models;
import  jakarta.persistence.*;


@Entity
@Table(name= "bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asume que idpieza es autoincremental (SERIAL en PostgreSQL)
    @Column(name = "idpieza")
    private Long idPieza;

    @Column(name = "nombre", length = 150, nullable = false) // VARCHAR(150), no nulo
    private String nombre;

    @Column(name = "numerodisponible", nullable = false)
    private Integer numeroDisponible;

    // Relación ManyToOne con Provedor (muchas piezas pueden ser de un proveedor)
    // FetchType.LAZY es generalmente recomendado para mejorar el rendimiento.
    // La columna 'proveedor_idproveedor' (o como la nombre Hibernate por defecto)
    // se creará en la tabla 'almacen' para la clave foránea.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprovedor", nullable = false) // Nombre de la columna de clave foránea en la tabla 'almacen'
    private Provedor provedor;

    public Bodega() {
    }

    public Bodega(String nombre, Integer numeroDisponible, Provedor provedor) {
        this.nombre = nombre;
        this.numeroDisponible = numeroDisponible;
        this.provedor = provedor;
    }

    public Long getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Long idPieza) {
        this.idPieza = idPieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroDisponible() {
        return numeroDisponible;
    }

    public void setNumeroDisponible(Integer numeroDisponible) {
        this.numeroDisponible = numeroDisponible;
    }

    public Provedor getProvedor() {
        return provedor;
    }

    public void setProvedor(Provedor proveedor) {
        this.provedor = proveedor;
    }
}
