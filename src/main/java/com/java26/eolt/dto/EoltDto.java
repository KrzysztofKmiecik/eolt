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
/*
*
* Numer,
*numer inwentarzowy,
* nazwa,
* nazwa projektu,
* numer projektu,
*  Numer AR z którego był kupiony,
* inżynier flow4,
* inżynier flow5,
* własność (nasza czy klienta) ,
* producent, rok produkcji,
* czy jest ESD,
* jakie rodziny ma ustawione z czasami cyklu,
* nazwa sieciowa,
* MAC adress,
*   ustawione/zablokowane typy,
* lokalizacja na hali,
* lokalizacja dokumentacji ( plików walidacyjnych, instrukcji pracy, karty parametrów, lista części zamiennych, bhp, ergonomia) ,
* dostawca,
* system(win7,xp,itp.)
 * */