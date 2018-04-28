package com.form3.payment.dao;

import com.form3.payment.model.PaymentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PaymentDaoImpl implements IPaymentDao {

  @PersistenceContext private EntityManager manager;

  @Override
  public List<PaymentEntity> listAllPayments(int offset, int limit) {

    Query query = manager.createQuery("select pa from PaymentEntity pa order by pa.id");
    query.setFirstResult(offset);
    query.setMaxResults(limit);

    return query.getResultList();
  }

  @Override
  public PaymentEntity getPaymentByUuid(String uuid) throws NoResultException{
    return (PaymentEntity)
        manager
            .createQuery("select pa from PaymentEntity pa where pa.uuid = :UUID")
            .setParameter("UUID", uuid)
            .getSingleResult();
  }

  @Override
  public PaymentEntity getPaymentById(Long id) throws NoResultException {
    return manager.find(PaymentEntity.class, id);
  }

  @Override
  public void createPaymentEntity(PaymentEntity paymentEntity) {
    manager.persist(paymentEntity);
    manager.flush();
  }

  @Override
  public void updatePaymentEntity(PaymentEntity paymentEntity) {
    manager.merge(paymentEntity);
  }

  @Override
  public void deletePaymentEntity(PaymentEntity paymentEntity) {
    manager.remove(paymentEntity);
  }
}
