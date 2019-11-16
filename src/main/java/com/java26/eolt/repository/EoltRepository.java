package com.java26.eolt.repository;

import com.java26.eolt.entity.EoltEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EoltRepository extends JpaRepository<EoltEntity, Long> {

    Optional<EoltEntity> findByEoltName(String eoltName);


//    @Modifying
//    @Query("update EoltEntity eoltEntity set eoltEntity.location = ?1 where eoltEntity.id = ?2")
//    int setLocationForEoltEntity( String location, Long id);

    @Modifying
    @Query("update EoltEntity eoltEntity set eoltEntity.location = :location where eoltEntity.id = :id")
    int setLocationForEoltEntity(@Param("location") String location, @Param("id") Long id);

}
