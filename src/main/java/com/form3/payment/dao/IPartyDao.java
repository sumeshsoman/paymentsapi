package com.form3.payment.dao;

import com.form3.payment.model.PartyEntity;

import javax.persistence.NoResultException;

public interface IPartyDao {
  PartyEntity getParty(String account_number, String bank_id) throws NoResultException;
}
