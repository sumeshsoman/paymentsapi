package com.form3.payment.dao;

import com.form3.payment.model.PartyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional(dontRollbackOn = NoResultException.class)
public class IPartyDaoImpl implements IPartyDao {

  @PersistenceContext private EntityManager manager;

  @Override
  public PartyEntity getParty(String account_number, String bank_id) throws NoResultException {
    return (PartyEntity)
        manager
            .createQuery(
                "select pa from PartyEntity pa where pa.bankIdAccIdKey.account_number = :ACC_NUM and pa.bankIdAccIdKey.bank_id = :BANK_ID")
            .setParameter("ACC_NUM", account_number)
            .setParameter("BANK_ID", bank_id)
            .getSingleResult();
  }
}
