package com.java26.eolt.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Table(name = "eolt")
//@DynamicUpdate
public class EoltEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true)
    private String eoltName;

    @Column(nullable = false)
    private String location;

}
