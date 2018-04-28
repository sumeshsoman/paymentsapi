package com.form3.payment.model;

import com.form3.payment.model.constants.CurrencyEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "forex")
public class ForexEntity implements Serializable {

  private static final long serialVersionUID = -7603222525371631735L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "payment_attribute_id")
  private PaymentAttributes paymentAttributesForForex;

  private String contractReference;
  private Float exchangeRate;
  private Float originalAmount;
  private CurrencyEnum originalCurrency;

  public ForexEntity() {
  }

  public PaymentAttributes getPaymentAttributesForForex() {
    return paymentAttributesForForex;
  }

  public void setPaymentAttributesForForex(PaymentAttributes paymentAttributesForForex) {
    this.paymentAttributesForForex = paymentAttributesForForex;
  }

  public String getContractReference() {
    return contractReference;
  }

  public void setContractReference(String contractReference) {
    this.contractReference = contractReference;
  }

  public Float getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(Float exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public Float getOriginalAmount() {
    return originalAmount;
  }

  public void setOriginalAmount(Float originalAmount) {
    this.originalAmount = originalAmount;
  }

  public CurrencyEnum getOriginalCurrency() {
    return originalCurrency;
  }

  public void setOriginalCurrency(CurrencyEnum originalCurrency) {
    this.originalCurrency = originalCurrency;
  }
}
