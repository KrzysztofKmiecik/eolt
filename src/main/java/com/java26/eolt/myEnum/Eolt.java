package com.java26.eolt.myEnum;

import lombok.Data;

@Data
public class Eolt {
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
