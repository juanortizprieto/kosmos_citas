package com.kosmos.citas.repository;

import com.kosmos.citas.model.CitasEntity;
import com.kosmos.citas.model.ConsultoriosEntity;
import com.kosmos.citas.model.DoctoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository<CitasEntity,Long> {
    List<CitasEntity> findOneByConsultorioAndFechaConsulta(ConsultoriosEntity consultorio, ZonedDateTime fechaConsulta);

    List<CitasEntity> findOneByDoctorAndFechaConsulta(DoctoresEntity doctor, ZonedDateTime fechaConsulta);

    CitasEntity findOneByNombrePaciente(String nombrePaciente);

    List<CitasEntity> findAllByDoctorAndFechaConsulta(DoctoresEntity doctor, ZonedDateTime fechaConsulta);

    List<CitasEntity> findAllByConsultorioAndFechaConsulta(ConsultoriosEntity consultorio, ZonedDateTime fechaConsulta);

    List<CitasEntity> findAllByNombrePaciente(String nombrePaciente);

    List<CitasEntity> findAllByDoctorAndFechaConsultaBetween(DoctoresEntity doctor, ZonedDateTime localDateTime, ZonedDateTime with);

    List<CitasEntity> findAllByDoctorAndFechaConsultaGreaterThanAndFechaConsultaLessThan(DoctoresEntity doctor, ZonedDateTime with, ZonedDateTime with1);
}
