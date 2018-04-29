package com.form3.payment.dao;

import com.form3.payment.model.PaymentEntity;

import javax.persistence.NoResultException;
import java.util.List;

public interface IPaymentDao {

  List<PaymentEntity> listAllPayments(int offset, int limit);

  PaymentEntity getPaymentByUuid(String uuid) throws NoResultException;

  PaymentEntity getPaymentById(Long id) throws NoResultException;

  void createPaymentEntity(PaymentEntity paymentEntity);

  void updatePaymentEntity(PaymentEntity paymentEntity);

  void deletePaymentEntity(PaymentEntity paymentEntity);
}
