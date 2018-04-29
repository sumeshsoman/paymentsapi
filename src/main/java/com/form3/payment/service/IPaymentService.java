package com.form3.payment.service;

import com.form3.payment.dto.PaymentWrapperDTO;

public interface IPaymentService {

  PaymentWrapperDTO getAllPayments(int start, int offset);

  PaymentWrapperDTO getPaymentsById(String id);

  void createPayment(PaymentWrapperDTO dto);

  void updatePayment(PaymentWrapperDTO dto);

  void deletePaymentById(String id);
}
