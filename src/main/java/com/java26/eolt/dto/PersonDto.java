package com.java26.eolt.dto;

import com.java26.eolt.myEnum.Responsibility;
import lombok.Data;

import javax.persistence.Entity;

@Data
public class PersonDto {
    private String name;
    private String surname;
    private Responsibility responsibilty;
}
