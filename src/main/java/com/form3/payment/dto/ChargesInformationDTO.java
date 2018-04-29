package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ChargesInformationDTO implements Serializable {
  private static final long serialVersionUID = -3792687113425876L;

  @JsonProperty("bearer_code")
  private String bearerCode;

  @JsonProperty("sender_charges")
  private List<ChargesObjectDTO> senderCharges;

  @JsonProperty("receiver_charges_amount")
  private String receiverChargesAmt;

  @JsonProperty("receiver_charges_currency")
  private String receiverChargesCurrency;

  public String getBearerCode() {
    return bearerCode;
  }

  public void setBearerCode(String bearerCode) {
    this.bearerCode = bearerCode;
  }

  public List<ChargesObjectDTO> getSenderCharges() {
    return senderCharges;
  }

  public void setSenderCharges(List<ChargesObjectDTO> senderCharges) {
    this.senderCharges = senderCharges;
  }

  public String getReceiverChargesAmt() {
    return receiverChargesAmt;
  }

  public void setReceiverChargesAmt(String receiverChargesAmt) {
    this.receiverChargesAmt = receiverChargesAmt;
  }

  public String getReceiverChargesCurrency() {
    return receiverChargesCurrency;
  }

  public void setReceiverChargesCurrency(String receiverChargesCurrency) {
    this.receiverChargesCurrency = receiverChargesCurrency;
  }
}
