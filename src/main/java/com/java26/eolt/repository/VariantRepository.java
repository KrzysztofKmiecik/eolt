package com.java26.eolt.repository;

import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariantRepository extends JpaRepository<VariantEntity, Long> {

  //  Optional<VariantEntity> findByDpn(String dpn);
    Optional<VariantEntity> findByDpnAndEolt(String dpn,EoltEntity eolt);
    List<VariantEntity> findByEolt(EoltEntity eolt);

}
