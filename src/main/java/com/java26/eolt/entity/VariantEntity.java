package com.java26.eolt.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "variant")
public class VariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dpn;


}
