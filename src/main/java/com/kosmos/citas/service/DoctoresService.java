package com.kosmos.citas.service;

import com.kosmos.citas.model.DoctoresEntity;

import java.util.List;

public interface DoctoresService {
    List<DoctoresEntity> findAll();

    DoctoresEntity findOneByid(Long idDoctor);
}
