package com.java26.eolt.repository;

import com.java26.eolt.entity.EoltEntity;
import com.java26.eolt.entity.VariantEntity;
import com.java26.eolt.entity.VariantHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VariantHistoryRepository extends JpaRepository<VariantHistoryEntity, Long> {

    List<VariantHistoryEntity> findByDpn(String dpn);

    List<VariantHistoryEntity> findByDpnAndEolt(String dpn, EoltEntity eolt);

    List<VariantHistoryEntity> findByEolt(EoltEntity eolt);

}
