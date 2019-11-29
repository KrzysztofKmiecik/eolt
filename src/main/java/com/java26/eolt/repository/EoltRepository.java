package com.java26.eolt.repository;

import com.java26.eolt.entity.EoltEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EoltRepository extends JpaRepository<EoltEntity, Long> {

    Optional<EoltEntity> findByEoltName(String eoltName);

    // List<EoltEntity> findByEoltNameContaining(String searchString);

//    @Modifying
//    @Query("update EoltEntity eoltEntity set eoltEntity.location = ?1 where eoltEntity.id = ?2")
//    int setLocationForEoltEntity( String location, Long id);

    //  @Query("select e from EoltEntity e where e.eoltName like %:searchString% or e.location like %:searchString%")

    @Query("select e from EoltEntity e where (  ((e.eoltName like %:searchString% or e.location like %:searchString%) or" +
                                              " (e.assetNumber like %:searchString% or  e.AR like %:searchString%)) or  " +

                                              "(((e.netName like %:searchString% or  e.macAdress like %:searchString%) or " +
                                               "(e.productionYear like %:searchString% or  e.supplierName like %:searchString%)) or" +
                                               "(e.systemVersion like %:searchString% or  e.documentationLink like %:searchString%))  )"   )
    List<EoltEntity> findMyEoltInEoltNameORLocationWithSearchString(@Param("searchString") String searchString);





}


