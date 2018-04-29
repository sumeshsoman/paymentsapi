package com.form3.payment.modelmapper;

import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.model.PaymentEntity;

public interface IDTOEntityMapper {
  PaymentEntity populatePaymentEntity(PaymentEntityDTO dto, PaymentEntity entity);
}
