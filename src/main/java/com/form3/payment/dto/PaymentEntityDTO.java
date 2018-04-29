package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PaymentEntityDTO implements Serializable {

  private static final long serialVersionUID = -7143209025381202961L;

  @JsonProperty("type")
  private String type;

  @JsonProperty("id")
  private String uuid;

  @JsonProperty("organisation_id")
  private String organisation_id;

  @JsonProperty("version")
  private Integer version;

  @JsonProperty("attributes")
  private PaymentAttributesDTO attributes;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getOrganisation_id() {
    return organisation_id;
  }

  public void setOrganisation_id(String organisation_id) {
    this.organisation_id = organisation_id;
  }

  public PaymentAttributesDTO getAttributes() {
    return attributes;
  }

  public void setAttributes(PaymentAttributesDTO attributes) {
    this.attributes = attributes;
  }
}
