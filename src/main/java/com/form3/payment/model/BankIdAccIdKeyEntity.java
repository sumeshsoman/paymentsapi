package com.form3.payment.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BankIdAccIdKeyEntity  implements Serializable {

  private static final long serialVersionUID = -6433166506632354753L;

  @NotNull
  @Size(max = 20)
  private String account_number;

  @NotNull
  @Size(max = 20)
  private String bank_id;

    public BankIdAccIdKeyEntity() {
    }

    public BankIdAccIdKeyEntity(String account_number, String bank_id) {
    this.account_number = account_number;
    this.bank_id = bank_id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BankIdAccIdKeyEntity that = (BankIdAccIdKeyEntity) o;
    return Objects.equals(account_number, that.account_number)
        && Objects.equals(bank_id, that.bank_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(account_number, bank_id);
  }
}
