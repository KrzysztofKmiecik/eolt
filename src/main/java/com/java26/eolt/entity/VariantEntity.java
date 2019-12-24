package com.java26.eolt.entity;

import com.java26.eolt.myEnum.Customer;
import com.java26.eolt.myEnum.Variant;
import com.java26.eolt.myEnum.VariantStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "variant")
public class VariantEntity extends Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dpn;

    @Column(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private Integer machineCycleTime;

    @Column(nullable = false)
    private Integer fixture;

    @Column(nullable = false)
    private String testEng;

    @Column(nullable = false)
    private String qualityEng;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VariantStatus variantStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eolt_id")
    private EoltEntity eolt;

   /* @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;*/
}
