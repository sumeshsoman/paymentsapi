package com.form3.payment.model;

import com.form3.payment.model.constants.CurrencyEnum;
import com.form3.payment.model.constants.PaymentSchemeEnum;
import com.form3.payment.model.constants.PaymentTypeEnum;
import com.form3.payment.model.constants.Scheme_Payment_Sub_Type_Enum;
import com.form3.payment.model.constants.Scheme_Payment_Type_Enum;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "payment_attribute")
public class PaymentAttributes implements Serializable {

  private static final long serialVersionUID = -3447019537743852786L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Float amount;

  private CurrencyEnum currency;

  private String end_reference;

  private Long numeric_ref;

  private Long payment_id;

  private String payment_purpose;

  private PaymentSchemeEnum paymentScheme;

  private PaymentTypeEnum paymentType;

  private String processingDate;

  private String reference;

  private Scheme_Payment_Sub_Type_Enum schemePaymentSubType;

  private Scheme_Payment_Type_Enum schemePaymentType;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumns({
    @JoinColumn(name = "beneficiary_party_id"),
    @JoinColumn(name = "beneficiary_party_bank_id")
  })
  private PartyEntity beneficiaryParty;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumns({@JoinColumn(name = "debtor_party_id"), @JoinColumn(name = "debtor_party_bank_id")})
  private PartyEntity debtorParty;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumns({
    @JoinColumn(name = "sponsor_party_id"),
    @JoinColumn(name = "sponsor_party_bank_id")
  })
  private PartyEntity sponsorParty;

  @OneToOne(mappedBy = "paymentAttributesForCharges", cascade = CascadeType.ALL)
  private ChargesInformationEntity chargesInformation;

  @OneToOne(mappedBy = "paymentAttributesForForex", cascade = CascadeType.ALL)
  private ForexEntity forexEntity;

  @OneToOne
  @JoinColumn(name = "payment_table_id")
  private PaymentEntity paymentEntity;

    public PaymentAttributes() {
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

  public String getEnd_reference() {
    return end_reference;
  }

  public void setEnd_reference(String end_reference) {
    this.end_reference = end_reference;
  }

  public Long getNumeric_ref() {
    return numeric_ref;
  }

  public void setNumeric_ref(Long numeric_ref) {
    this.numeric_ref = numeric_ref;
  }

  public Long getPayment_id() {
    return payment_id;
  }

  public void setPayment_id(Long payment_id) {
    this.payment_id = payment_id;
  }

  public String getPayment_purpose() {
    return payment_purpose;
  }

  public void setPayment_purpose(String payment_purpose) {
    this.payment_purpose = payment_purpose;
  }

  public PaymentSchemeEnum getPaymentScheme() {
    return paymentScheme;
  }

  public void setPaymentScheme(PaymentSchemeEnum paymentScheme) {
    this.paymentScheme = paymentScheme;
  }

  public PaymentTypeEnum getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentTypeEnum paymentType) {
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

  public Scheme_Payment_Sub_Type_Enum getSchemePaymentSubType() {
    return schemePaymentSubType;
  }

  public void setSchemePaymentSubType(Scheme_Payment_Sub_Type_Enum schemePaymentSubType) {
    this.schemePaymentSubType = schemePaymentSubType;
  }

  public Scheme_Payment_Type_Enum getSchemePaymentType() {
    return schemePaymentType;
  }

  public void setSchemePaymentType(Scheme_Payment_Type_Enum schemePaymentType) {
    this.schemePaymentType = schemePaymentType;
  }

  public PartyEntity getBeneficiaryParty() {
    return beneficiaryParty;
  }

  public void setBeneficiaryParty(PartyEntity beneficiaryParty) {
    this.beneficiaryParty = beneficiaryParty;
  }

  public PartyEntity getDebtorParty() {
    return debtorParty;
  }

  public void setDebtorParty(PartyEntity debtorParty) {
    this.debtorParty = debtorParty;
  }

  public ChargesInformationEntity getChargesInformation() {
    return chargesInformation;
  }

  public void setChargesInformation(ChargesInformationEntity chargesInformation) {
    this.chargesInformation = chargesInformation;
    chargesInformation.setPaymentAttributesForCharges(this);
  }

  public ForexEntity getForexEntity() {
    return forexEntity;
  }

  public void setForexEntity(ForexEntity forexEntity) {
    this.forexEntity = forexEntity;
    forexEntity.setPaymentAttributesForForex(this);
  }

  public PartyEntity getSponsorParty() {
    return sponsorParty;
  }

  public void setSponsorParty(PartyEntity sponsorParty) {
    this.sponsorParty = sponsorParty;
  }
}
