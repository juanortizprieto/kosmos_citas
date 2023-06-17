package com.kosmos.citas.repository;

import com.kosmos.citas.model.DoctoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctoresRepository extends JpaRepository<DoctoresEntity,Long> {
}
