package com.form3.payment.model.constants;

public enum Scheme_Payment_Sub_Type_Enum {
  INTERNETBANKING("InternetBanking"),
  INVALID("Invalid");

  private String name;

  Scheme_Payment_Sub_Type_Enum(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
