package com.kosmos.citas.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "citas")
public class CitasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private DoctoresEntity doctor;

    @ManyToOne
    @JoinColumn(name = "id_consultorio", nullable = false)
    private ConsultoriosEntity consultorio;

    @ManyToOne
    @JoinColumn(name = "id_estatus", nullable = false)
    private EstatusCitaEntity estatusCita;

    @Column(name ="fecha_consulta")
    private ZonedDateTime fechaConsulta;

    @Column(name ="nombre_paciente")
    private String nombrePaciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoctoresEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctoresEntity doctor) {
        this.doctor = doctor;
    }

    public ConsultoriosEntity getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(ConsultoriosEntity consultorio) {
        this.consultorio = consultorio;
    }

    public EstatusCitaEntity getEstatusCita() {
        return estatusCita;
    }

    public void setEstatusCita(EstatusCitaEntity estatusCita) {
        this.estatusCita = estatusCita;
    }

    public ZonedDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(ZonedDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}
