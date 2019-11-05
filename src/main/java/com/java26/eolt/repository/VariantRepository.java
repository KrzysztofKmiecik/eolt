package com.java26.eolt.repository;

import com.java26.eolt.entity.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VariantRepository extends JpaRepository<VariantEntity, Long> {

    Optional<VariantEntity> findByDpn(String dpn);
}
