package com.java26.eolt.myEnum;

import lombok.Data;

@Data
public class Variant {
    private String dpn;
    private Customer customer;
    private Integer machineCycleTime;
    private Integer fixture;
    private String testEng;
    private String qualityEng;
    private VariantStatus variantStatus;

}
