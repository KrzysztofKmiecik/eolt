package com.java26.eolt.entity;

import com.java26.eolt.myEnum.SupplierName;
import com.java26.eolt.myEnum.SystemVersion;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "eolt")
public class EoltEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String eoltName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String assetNumber;

    @Column(nullable = false)
    private String AR;

    @Column(nullable = false)
    private String netName;

    @Column(nullable = false)
    private String macAdress;

    @Column(nullable = false)
    private Integer productionYear;

    @Column(nullable = false)
    private SupplierName supplierName;

    @Column(nullable = false)
    private SystemVersion systemVersion;

    @Column(nullable = false)
    private String documentationLink;

}
