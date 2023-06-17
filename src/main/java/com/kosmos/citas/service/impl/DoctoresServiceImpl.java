package com.kosmos.citas.service.impl;

import com.kosmos.citas.model.DoctoresEntity;
import com.kosmos.citas.repository.DoctoresRepository;
import com.kosmos.citas.service.DoctoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctoresServiceImpl implements DoctoresService {

    @Autowired
    DoctoresRepository doctoresRepository;
    @Override
    public List<DoctoresEntity> findAll() {
        return doctoresRepository.findAll();
    }

    @Override
    public DoctoresEntity findOneByid(Long idDoctor) {
        return doctoresRepository.findById(idDoctor).get();
    }
}
