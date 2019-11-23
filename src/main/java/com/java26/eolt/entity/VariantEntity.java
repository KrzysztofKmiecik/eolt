package com.java26.eolt.entity;

import com.java26.eolt.dto.PersonDto;
import com.java26.eolt.myEnum.Customer;
import com.java26.eolt.myEnum.VariantStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "variant")
public class VariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dpn;

    @Column(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private Integer machineCycleTime;

    @Column(nullable = false)
    private Integer fixture;

    @ManyToOne(optional = false)
    @JoinColumn(name = "testEng_id")
    private PersonEntity testEng;

    @ManyToOne(optional = false)
    @JoinColumn(name = "qualityEng_id")
    private PersonEntity qualityEng;

    @Column(nullable = false)
    private VariantStatus variantStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eolt_id")
    private EoltEntity eolt;



}
