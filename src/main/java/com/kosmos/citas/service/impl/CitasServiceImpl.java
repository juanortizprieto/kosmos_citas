package com.kosmos.citas.service.impl;

import com.kosmos.citas.dto.CitasDTO;
import com.kosmos.citas.dto.ResultDto;
import com.kosmos.citas.model.CitasEntity;
import com.kosmos.citas.model.EstatusCitaEntity;
import com.kosmos.citas.repository.CitasRepository;
import com.kosmos.citas.service.CitasService;
import com.kosmos.citas.service.ConsultoriosService;
import com.kosmos.citas.service.DoctoresService;
import com.kosmos.citas.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    CitasRepository citasRepository;

    @Autowired
    DoctoresService doctoresService;

    @Autowired
    ConsultoriosService consultoriosService;

    @Override
    public ResultDto save(CitasDTO cita) {
        ResultDto result = new ResultDto();

        CitasEntity entity = getCitaEntity(result,cita);
        if(result.isExito()) {

            List<CitasEntity> citasEntity = citasRepository.findAllByConsultorioAndFechaConsulta(entity.getConsultorio(), entity.getFechaConsulta());
            if (!citasEntity.isEmpty()) {
                result.setExito(false);
                result.setMensaje("Existe cita registrada para el consultorio en la fecha y hora seleccionadas");
            }

            citasEntity = citasRepository.findAllByDoctorAndFechaConsulta(entity.getDoctor(), entity.getFechaConsulta());

            if (!citasEntity.isEmpty()) {
                result.setExito(false);
                result.setMensaje("Existe cita registrada para el doctor en la fecha y hora seleccionadas");
            }

            citasEntity = citasRepository.findAllByNombrePaciente(entity.getNombrePaciente());
            if (!citasEntity.isEmpty()) {
                for (CitasEntity citaActual : citasEntity) {
                    Long diferenciaCita = Duration.between(citaActual.getFechaConsulta(), entity.getFechaConsulta()).toHours();
                    if (diferenciaCita < 2) {
                        result.setExito(false);
                        result.setMensaje("Deben pasar al menos 2 horas para agendar una nueva cita");
                        break;
                    }
                }

            }
            citasEntity = citasRepository.findAllByDoctorAndFechaConsultaGreaterThanAndFechaConsultaLessThan(entity.getDoctor(), LocalDateTime.of(entity.getFechaConsulta().toLocalDate(), LocalTime.MIN).atZone(ZoneId.systemDefault()), LocalDateTime.of(entity.getFechaConsulta().toLocalDate(), LocalTime.MAX).atZone(ZoneId.systemDefault()));

            if (citasEntity.size() >= 8) {
                result.setExito(false);
                result.setMensaje("El doctor no tiene mas citas disponibles");
            }

            if (result.isExito()) {
                citasRepository.saveAndFlush(entity);
            }
        }

            return result;




    }

    private CitasEntity getCitaEntity(ResultDto result, CitasDTO cita) {
            CitasEntity entity = new CitasEntity();
            result.setExito(true);
            Date fechaCita;
            if(cita.getNombrePaciente()==null||"".equals(cita.getNombrePaciente().trim())){
                result.setExito(false);
                result.setMensaje("Debe especificar el nombre del paciente");
            }

            if(cita.getIdConsultorio()<=0){
                result.setExito(false);
                result.setMensaje("Debe seleccionar un consultorio");
            }

            if(cita.getIdDoctor()<=0){
                result.setExito(false);
                result.setMensaje("Debe seleccionar un Doctor");
            }

            if(cita.getFecha()==null||"".equals(cita.getFecha().trim())){
                result.setExito(false);
                result.setMensaje("Debe ingresar la fecha de la cita");
            }else{
                try {
                    fechaCita = Utils.getDate(cita.getFecha());

                } catch (ParseException e) {
                    result.setExito(false);
                    result.setMensaje("La fecha debe tener formato dd/mm/yyyy");
                }
            }

            if(cita.getHora()==null||"".equals(cita.getHora().trim())){

                result.setExito(false);
                result.setMensaje("Debe ingresar la hora de la cita");
            }else{
                try {
                    fechaCita = Utils.getHora(cita.getHora());

                } catch (ParseException e) {
                    result.setExito(false);
                    result.setMensaje("La hora debe tener formato HH(24):mm");
                }
            }

            if(result.isExito()){
                EstatusCitaEntity estatusEntity = new EstatusCitaEntity();
                estatusEntity.setId(1L);
                entity.setConsultorio(consultoriosService.findOneByid(cita.getIdDoctor()));
                entity.setEstatusCita(estatusEntity);
                entity.setDoctor(doctoresService.findOneByid(cita.getIdDoctor()));
                entity.setNombrePaciente(cita.getNombrePaciente());
                entity.setFechaConsulta(Utils.getZoneDateTime(cita.getFecha()+" "+cita.getHora()));
            }

            return entity;


    }


}
