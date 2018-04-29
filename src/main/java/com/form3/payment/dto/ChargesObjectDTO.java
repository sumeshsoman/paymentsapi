package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ChargesObjectDTO implements Serializable {
  private static final long serialVersionUID = 7180546593436328862L;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("currency")
  private String currency;

  public ChargesObjectDTO(String amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public ChargesObjectDTO() {}

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
