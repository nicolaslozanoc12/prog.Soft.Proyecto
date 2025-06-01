package com.software1.models;

public class SolicitudInsumos {
    private String nombreProveedor;
    private Long insumo;
    private Integer cantidad;

    public SolicitudInsumos(String nombreProveedor, Long insumo, Integer cantidad) {
        this.nombreProveedor = nombreProveedor;
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Long getInsumo() {
        return insumo;
    }

    public void setInsumo(Long insumo) {
        this.insumo = insumo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
