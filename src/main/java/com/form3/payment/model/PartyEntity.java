package com.form3.payment.model;

import com.form3.payment.model.constants.AccountTypeEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "party")
public class PartyEntity implements Serializable {

  private static final long serialVersionUID = -7344385813598542999L;

  @EmbeddedId private BankIdAccIdKeyEntity bankIdAccIdKey;

  private String accountName;

  private String accNumberCode;

  private AccountTypeEnum accountType;

  @Column(length = 1024)
  private String address;

  @Column(length = 20)
  private String bankIdCode;

  private String name;

  @OneToMany(mappedBy = "beneficiaryParty")
  private Set<PaymentAttributes> beneficiaryPaymentAttributes = new HashSet<>();

  @OneToMany(mappedBy = "debtorParty")
  private Set<PaymentAttributes> debtorPaymentAttributes = new HashSet<>();

  @OneToMany(mappedBy = "sponsorParty")
  private Set<PaymentAttributes> sponsorPaymentAttributes = new HashSet<>();

  public PartyEntity() {
  }

  public BankIdAccIdKeyEntity getBankIdAccIdKey() {
    return bankIdAccIdKey;
  }

  public void setBankIdAccIdKey(BankIdAccIdKeyEntity bankIdAccIdKey) {
    this.bankIdAccIdKey = bankIdAccIdKey;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccNumberCode() {
    return accNumberCode;
  }

  public void setAccNumberCode(String accNumberCode) {
    this.accNumberCode = accNumberCode;
  }

  public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBankIdCode() {
    return bankIdCode;
  }

  public void setBankIdCode(String bankIdCode) {
    this.bankIdCode = bankIdCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<PaymentAttributes> getBeneficiaryPaymentAttributes() {
    return beneficiaryPaymentAttributes;
  }

  public void setBeneficiaryPaymentAttributes(Set<PaymentAttributes> beneficiaryPaymentAttributes) {
    this.beneficiaryPaymentAttributes = beneficiaryPaymentAttributes;
  }

  public void addBeneficiaryPaymentAttributes(PaymentAttributes beneficiaryPaymentAttribute) {
    this.beneficiaryPaymentAttributes.add(beneficiaryPaymentAttribute);
    beneficiaryPaymentAttribute.setBeneficiaryParty(this);
  }
  public Set<PaymentAttributes> getDebtorPaymentAttributes() {
    return debtorPaymentAttributes;
  }

  public void setDebtorPaymentAttributes(Set<PaymentAttributes> debtorPaymentAttributes) {
    this.debtorPaymentAttributes = debtorPaymentAttributes;
  }
  public void addDebtorPaymentAttributes(PaymentAttributes debtorPaymentAttribute) {
    this.debtorPaymentAttributes.add(debtorPaymentAttribute);
    debtorPaymentAttribute.setDebtorParty(this);
  }

  public Set<PaymentAttributes> getSponsorPaymentAttributes() {
    return sponsorPaymentAttributes;
  }

  public void setSponsorPaymentAttributes(Set<PaymentAttributes> sponsorPaymentAttributes) {
    this.sponsorPaymentAttributes = sponsorPaymentAttributes;
  }

  public void addSponsorPaymentAttributes(PaymentAttributes sponsorPaymentAttribute) {
    this.sponsorPaymentAttributes.add(sponsorPaymentAttribute);
    sponsorPaymentAttribute.setSponsorParty(this);
  }
}
