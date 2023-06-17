package com.kosmos.citas.service;

import com.kosmos.citas.model.ConsultoriosEntity;

import java.util.List;

public interface ConsultoriosService {
    List<ConsultoriosEntity> findAll();

    ConsultoriosEntity findOneByid(Long idDoctor);
}
