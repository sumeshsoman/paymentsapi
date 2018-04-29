package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PaymentLinksDTO implements Serializable {

  private static final long serialVersionUID = 844174293432061480L;

  @JsonProperty("self")
  private String self;

  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }
}
