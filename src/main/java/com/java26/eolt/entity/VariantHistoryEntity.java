package com.java26.eolt.entity;

import com.java26.eolt.myEnum.Customer;
import com.java26.eolt.myEnum.ModificationReason;
import com.java26.eolt.myEnum.Variant;
import com.java26.eolt.myEnum.VariantStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "variantHistory")
public class VariantHistoryEntity extends Variant {
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

    @Column(nullable = false)
    private LocalDateTime modificationDateTime;

    @Enumerated
    @Column(nullable = false)
    private ModificationReason modificationReason;

    @Column(nullable = false)
    private String descriptionOfChange;

}
