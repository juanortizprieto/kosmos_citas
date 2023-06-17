package com.kosmos.citas.repository;

import com.kosmos.citas.model.ConsultoriosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoriosRepository extends JpaRepository<ConsultoriosEntity,Long> {
}
