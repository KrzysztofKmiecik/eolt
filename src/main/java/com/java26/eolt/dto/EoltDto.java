package com.java26.eolt.dto;

import com.java26.eolt.myEnum.SupplierName;
import com.java26.eolt.myEnum.SystemVersion;
import lombok.Data;

@Data
public class EoltDto {
    private String eoltName;
    private String location;
    private String assetNumber;
    private String AR;
    private String netName;
    private String macAdress;
    private Integer productionYear;
    private SupplierName supplierName;
    private SystemVersion systemVersion;
    private String documentationLink;

}
