package com.form3.payment.modelmapper;

import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.model.PaymentEntity;

public interface IEntityDTOMapper {
    PaymentEntityDTO getPaymentEntityDTO(PaymentEntity entity);
}
