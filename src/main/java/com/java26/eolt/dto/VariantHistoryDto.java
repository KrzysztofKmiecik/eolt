package com.java26.eolt.dto;

import com.java26.eolt.myEnum.Customer;
import com.java26.eolt.myEnum.Variant;
import com.java26.eolt.myEnum.VariantStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VariantHistoryDto extends Variant {
    private String dpn;
    private Customer customer;
    private Integer machineCycleTime;
    private Integer fixture;
    private String testEng;
    private String qualityEng;
    private VariantStatus variantStatus;
    private String descriptionOfChange;


}
