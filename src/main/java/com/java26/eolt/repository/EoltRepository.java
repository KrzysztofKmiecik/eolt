package com.java26.eolt.repository;

import com.java26.eolt.entity.EoltEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EoltRepository extends JpaRepository<EoltEntity,Long> {

    Optional<EoltEntity> findByEoltName(String eoltName);
}
