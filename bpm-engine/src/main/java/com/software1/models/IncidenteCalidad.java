package com.software1.models;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "incidentes_calidad")
public class IncidenteCalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asume que id_incidente es SERIAL
    @Column(name = "id_incidente")
    private Integer idIncidente;

    @Column(name = "id_linea", nullable = false)
    private Long idLinea; // Se asume que tienes una entidad Linea o manejas el ID directamente

    @Column(name = "hora_alerta")
    private String horaAlerta;


    @Column(name = "causa_raiz", columnDefinition = "TEXT")
    private String causaRaiz;

    @Column(name = "accion_correctiva", columnDefinition = "TEXT")
    private String accionCorrectiva;

    @Column(name = "calidad")
    private Boolean calidad;

    @Column(name = "hora_final")
    private OffsetDateTime horaFinal; // TIMESTAMPTZ se mapea a OffsetDateTime
    // Constructor vacío necesario para JPA
    public IncidenteCalidad() {
    }
    // Constructor con todos los campos
    public IncidenteCalidad(Long idLinea, String horaAlerta, String causaRaiz, String accionCorrectiva, Boolean calidad) {
        this.idLinea = idLinea;
        this.horaAlerta = horaAlerta;
        this.causaRaiz = causaRaiz;
        this.accionCorrectiva = accionCorrectiva;
        this.calidad = calidad;
    }

    public Integer getIdIncidente() {
        return idIncidente;
    }

    public void setIdIncidente(Integer idIncidente) {
        this.idIncidente = idIncidente;
    }

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
        this.idLinea = idLinea;
    }

    public String getHoraAlerta() {
        return horaAlerta;
    }

    public void setHoraAlerta(String horaAlerta) {
        this.horaAlerta = horaAlerta;
    }

    public String getCausaRaiz() {
        return causaRaiz;
    }

    public void setCausaRaiz(String causaRaiz) {
        this.causaRaiz = causaRaiz;
    }

    public String getAccionCorrectiva() {
        return accionCorrectiva;
    }

    public void setAccionCorrectiva(String accionCorrectiva) {
        this.accionCorrectiva = accionCorrectiva;
    }

    public Boolean getCalidad() {
        return calidad;
    }

    public void setCalidad(Boolean calidad) {
        this.calidad = calidad;
    }

    public OffsetDateTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(OffsetDateTime horaFinal) {
        this.horaFinal = horaFinal;
    }

}
