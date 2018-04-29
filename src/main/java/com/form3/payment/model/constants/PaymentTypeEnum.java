package com.form3.payment.model.constants;

public enum PaymentTypeEnum {
  CREDIT("Credit"),
  DEBIT("Debit"),
  INVALID("Invalid");

  private String name;

  PaymentTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
