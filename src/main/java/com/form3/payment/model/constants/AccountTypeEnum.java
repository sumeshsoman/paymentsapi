package com.form3.payment.model.constants;

public enum AccountTypeEnum {
  SAVINGS,
  CURRENTACCOUNT,
  INVALID;

  public static AccountTypeEnum getEnumOnOrdinal(String value){
    int ordinal = Integer.parseInt(value);
    if(ordinal == 0){
      return SAVINGS;
    }else if(ordinal == 1){
      return CURRENTACCOUNT;
    }else {
      return INVALID;
      }
  }
}
