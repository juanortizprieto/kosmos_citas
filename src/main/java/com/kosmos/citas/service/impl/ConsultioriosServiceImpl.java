package com.kosmos.citas.service.impl;

import com.kosmos.citas.model.ConsultoriosEntity;
import com.kosmos.citas.repository.ConsultoriosRepository;
import com.kosmos.citas.service.ConsultoriosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConsultioriosServiceImpl implements ConsultoriosService {

    @Autowired
    ConsultoriosRepository consultoriosRepository;

    @Override
    public List<ConsultoriosEntity> findAll() {
        return consultoriosRepository.findAll();
    }

    @Override
    public ConsultoriosEntity findOneByid(Long idDoctor) {
        return consultoriosRepository.findById(idDoctor).get();
    }
}
