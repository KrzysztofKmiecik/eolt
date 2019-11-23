package com.java26.eolt.entity;

import com.java26.eolt.myEnum.Responsibility;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Responsibility responsibilty;
}
