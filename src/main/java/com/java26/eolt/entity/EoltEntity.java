package com.java26.eolt.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "eolt")
public class EoltEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique =true)
    private String eoltName;

    @Column(nullable = false)
    private String location;


}
