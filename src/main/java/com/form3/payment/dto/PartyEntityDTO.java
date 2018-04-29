package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PartyEntityDTO implements Serializable {
  private static final long serialVersionUID = -503343487437067297L;

  @JsonProperty("account_name")
  private String accountName;

  @JsonProperty("account_number")
  private String account_number;

  @JsonProperty("bank_id")
  private String bank_id;

  @JsonProperty("account_number_code")
  private String accNumberCode;

  @JsonProperty("account_type")
  private String accountType;

  @JsonProperty("address")
  private String address;

  @JsonProperty("bank_id_code")
  private String bankIdCode;

  @JsonProperty("name")
  private String name;

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccount_number() {
    return account_number;
  }

  public void setAccount_number(String account_number) {
    this.account_number = account_number;
  }

  public String getBank_id() {
    return bank_id;
  }

  public void setBank_id(String bank_id) {
    this.bank_id = bank_id;
  }

  public String getAccNumberCode() {
    return accNumberCode;
  }

  public void setAccNumberCode(String accNumberCode) {
    this.accNumberCode = accNumberCode;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
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
}
