package com.form3.payment.modelmapper;

import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.model.PaymentEntity;
import org.modelmapper.PropertyMap;

public class PaymentDTOMap extends PropertyMap<PaymentEntityDTO, PaymentEntity> {
    @Override
    protected void configure() {
        map().setUuid(source.getUuid());
        map().setOrganisation_id(source.getOrganisation_id());
    }
}
