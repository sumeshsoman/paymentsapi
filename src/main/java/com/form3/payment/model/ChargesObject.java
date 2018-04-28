package com.form3.payment.model;

import com.form3.payment.model.constants.CurrencyEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ChargesObject implements Serializable {

  private static final long serialVersionUID = -5777829798238599L;
  private Float amount;
  private CurrencyEnum currency;

  public ChargesObject() {
  }

  public ChargesObject(Float amount, CurrencyEnum currency) {

    this.amount = amount;
    this.currency = currency;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public CurrencyEnum getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEnum currency) {
    this.currency = currency;
  }
}
