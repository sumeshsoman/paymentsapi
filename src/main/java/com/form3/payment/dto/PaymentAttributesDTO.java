package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PaymentAttributesDTO implements Serializable {
  private static final long serialVersionUID = -5072930528999213779L;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("end_to_end_reference")
  private String end_reference;

  @JsonProperty("numeric_reference")
  private String numeric_ref;

  @JsonProperty("payment_id")
  private String payment_id;

  @JsonProperty("payment_purpose")
  private String payment_purpose;

  @JsonProperty("payment_scheme")
  private String paymentScheme;

  @JsonProperty("payment_type")
  private String paymentType;

  @JsonProperty("processing_date")
  private String processingDate;

  @JsonProperty("reference")
  private String reference;

  @JsonProperty("scheme_payment_sub_type")
  private String schemePaymentSubType;

  @JsonProperty("scheme_payment_type")
  private String schemePaymentType;

  @JsonProperty("beneficiary_party")
  private PartyEntityDTO beneficiaryParty;

  @JsonProperty("debtor_party")
  private PartyEntityDTO debtorParty;

  @JsonProperty("sponsor_party")
  private PartyEntityDTO sponsorParty;

  @JsonProperty("charges_information")
  private ChargesInformationDTO chargesInformation;

  @JsonProperty("fx")
  private ForexDTO forex;

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

  public String getEnd_reference() {
    return end_reference;
  }

  public void setEnd_reference(String end_reference) {
    this.end_reference = end_reference;
  }

  public String getNumeric_ref() {
    return numeric_ref;
  }

  public void setNumeric_ref(String numeric_ref) {
    this.numeric_ref = numeric_ref;
  }

  public String getPayment_id() {
    return payment_id;
  }

  public void setPayment_id(String payment_id) {
    this.payment_id = payment_id;
  }

  public String getPayment_purpose() {
    return payment_purpose;
  }

  public void setPayment_purpose(String payment_purpose) {
    this.payment_purpose = payment_purpose;
  }

  public String getPaymentScheme() {
    return paymentScheme;
  }

  public void setPaymentScheme(String paymentScheme) {
    this.paymentScheme = paymentScheme;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getProcessingDate() {
    return processingDate;
  }

  public void setProcessingDate(String processingDate) {
    this.processingDate = processingDate;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public String getSchemePaymentSubType() {
    return schemePaymentSubType;
  }

  public void setSchemePaymentSubType(String schemePaymentSubType) {
    this.schemePaymentSubType = schemePaymentSubType;
  }

  public String getSchemePaymentType() {
    return schemePaymentType;
  }

  public void setSchemePaymentType(String schemePaymentType) {
    this.schemePaymentType = schemePaymentType;
  }

  public PartyEntityDTO getBeneficiaryParty() {
    return beneficiaryParty;
  }

  public void setBeneficiaryParty(PartyEntityDTO beneficiaryParty) {
    this.beneficiaryParty = beneficiaryParty;
  }

  public PartyEntityDTO getDebtorParty() {
    return debtorParty;
  }

  public void setDebtorParty(PartyEntityDTO debtorParty) {
    this.debtorParty = debtorParty;
  }

  public PartyEntityDTO getSponsorParty() {
    return sponsorParty;
  }

  public void setSponsorParty(PartyEntityDTO sponsorParty) {
    this.sponsorParty = sponsorParty;
  }

  public ChargesInformationDTO getChargesInformation() {
    return chargesInformation;
  }

  public void setChargesInformation(ChargesInformationDTO chargesInformation) {
    this.chargesInformation = chargesInformation;
  }

  public ForexDTO getForex() {
    return forex;
  }

  public void setForex(ForexDTO forex) {
    this.forex = forex;
  }
}
