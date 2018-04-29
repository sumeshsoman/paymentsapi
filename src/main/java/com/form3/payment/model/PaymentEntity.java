package com.form3.payment.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "payment_table")
public class PaymentEntity implements Serializable {

  private static final long serialVersionUID = -7515609145896642613L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String type = "Payment";
  private String uuid;
  private String organisation_id;
  private Integer version;

  @OneToOne(mappedBy = "paymentEntity", cascade = CascadeType.ALL)
  private PaymentAttributes paymentAttributes;

  public PaymentEntity() {}

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

  public String getOrganisation_id() {
    return organisation_id;
  }

  public void setOrganisation_id(String organisation_id) {
    this.organisation_id = organisation_id;
  }

  public Long getId() {
    return id;
  }

  public PaymentAttributes getPaymentAttributes() {
    return paymentAttributes;
  }

  public void setPaymentAttributes(PaymentAttributes paymentAttributes) {
    this.paymentAttributes = paymentAttributes;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
