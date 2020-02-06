package com.java26.eolt.dto;

import com.java26.eolt.myEnum.Customer;
import com.java26.eolt.myEnum.Variant;
import com.java26.eolt.myEnum.VariantStatus;
import lombok.Data;

import java.util.List;

@Data
public class VariantDtoExtended extends VariantDto {
  private String eoltNames;
  private String variants;

}
