package com.form3.payment.model;

import com.form3.payment.model.constants.CurrencyEnum;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "charges_information")
public class ChargesInformationEntity implements Serializable {

  private static final long serialVersionUID = 919818353418218142L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "payment_attribute_id")
  private PaymentAttributes paymentAttributesForCharges;

  private String bearerCode;

  @ElementCollection
  @CollectionTable(
    name = "CHARGESOBJECT",
    joinColumns = @JoinColumn(name = "charges_information_id")
  )
  private List<ChargesObject> senderCharges = new ArrayList<>();

  private Float receiverChargesAmt;

  private CurrencyEnum receiverChargesCurrency;

  public ChargesInformationEntity() {}

  public PaymentAttributes getPaymentAttributesForCharges() {
    return paymentAttributesForCharges;
  }

  public void setPaymentAttributesForCharges(PaymentAttributes paymentAttributesForCharges) {
    this.paymentAttributesForCharges = paymentAttributesForCharges;
  }

  public String getBearerCode() {
    return bearerCode;
  }

  public void setBearerCode(String bearerCode) {
    this.bearerCode = bearerCode;
  }

  public List<ChargesObject> getSenderCharges() {
    return senderCharges;
  }

  public void setSenderCharges(List<ChargesObject> senderCharges) {
    this.senderCharges = senderCharges;
  }

  public Float getReceiverChargesAmt() {
    return receiverChargesAmt;
  }

  public void setReceiverChargesAmt(Float receiverChargesAmt) {
    this.receiverChargesAmt = receiverChargesAmt;
  }

  public CurrencyEnum getReceiverChargesCurrency() {
    return receiverChargesCurrency;
  }

  public void setReceiverChargesCurrency(CurrencyEnum receiverChargesCurrency) {
    this.receiverChargesCurrency = receiverChargesCurrency;
  }
}
