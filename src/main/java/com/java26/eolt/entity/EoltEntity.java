package com.java26.eolt.entity;

import com.java26.eolt.myEnum.Eolt;
import com.java26.eolt.myEnum.SupplierName;
import com.java26.eolt.myEnum.SystemVersion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "eolt")
@Getter
@Setter
public class EoltEntity extends Eolt {
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SupplierName supplierName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemVersion systemVersion;

    @Column(nullable = false)
    private String documentationLink;

}
