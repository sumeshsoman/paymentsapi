package com.form3.payment.modelmapper;

import com.form3.payment.dto.PaymentAttributesDTO;
import com.form3.payment.model.PaymentAttributes;
import com.form3.payment.model.constants.CurrencyEnum;
import com.form3.payment.model.constants.PaymentSchemeEnum;
import com.form3.payment.model.constants.PaymentTypeEnum;
import com.form3.payment.model.constants.Scheme_Payment_Sub_Type_Enum;
import com.form3.payment.model.constants.Scheme_Payment_Type_Enum;
import org.modelmapper.PropertyMap;

public class PaymentAttributesDTOMap extends PropertyMap<PaymentAttributesDTO, PaymentAttributes> {

  @Override
  protected void configure() {

    map()
        .setCurrency(
            source.getCurrency() != null
                ? CurrencyEnum.valueOf(source.getCurrency())
                : CurrencyEnum.INVALID);
    map()
        .setNumeric_ref(
            source.getNumeric_ref() != null ? Long.valueOf(source.getNumeric_ref()) : null);
    map()
        .setPayment_id(
            source.getPayment_id() != null ? Long.valueOf(source.getPayment_id()) : null);
    map()
        .setPaymentScheme(
            source.getPaymentScheme() != null
                ? PaymentSchemeEnum.valueOf(source.getPaymentScheme())
                : PaymentSchemeEnum.INVALID);
    map()
        .setPaymentType(
            source.getPaymentType() != null
                ? PaymentTypeEnum.valueOf(source.getPaymentType())
                : PaymentTypeEnum.INVALID);
    map()
        .setSchemePaymentSubType(
            source.getSchemePaymentSubType() != null
                ? Scheme_Payment_Sub_Type_Enum.valueOf(source.getSchemePaymentSubType())
                : Scheme_Payment_Sub_Type_Enum.INVALID);
    map()
        .setSchemePaymentType(
            source.getSchementPaymentType() != null
                ? Scheme_Payment_Type_Enum.valueOf(source.getSchementPaymentType())
                : Scheme_Payment_Type_Enum.INVALID);
    map().setAmount(source.getAmount() != null ? Float.valueOf(source.getAmount()) : null);
  }
}
