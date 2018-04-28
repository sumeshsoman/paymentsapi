package com.form3.payment.modelmapper;

import com.form3.payment.dto.ChargesInformationDTO;
import com.form3.payment.dto.ForexDTO;
import com.form3.payment.dto.PartyEntityDTO;
import com.form3.payment.dto.PaymentAttributesDTO;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.model.BankIdAccIdKeyEntity;
import com.form3.payment.model.ChargesInformationEntity;
import com.form3.payment.model.ChargesObject;
import com.form3.payment.model.ForexEntity;
import com.form3.payment.model.PartyEntity;
import com.form3.payment.model.PaymentAttributes;
import com.form3.payment.model.PaymentEntity;
import com.form3.payment.model.constants.AccountTypeEnum;
import com.form3.payment.model.constants.CurrencyEnum;
import com.form3.payment.model.constants.PaymentSchemeEnum;
import com.form3.payment.model.constants.PaymentTypeEnum;
import com.form3.payment.model.constants.Scheme_Payment_Sub_Type_Enum;
import com.form3.payment.model.constants.Scheme_Payment_Type_Enum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOEntityMapper {

  public PaymentEntity populatePaymentEntity(PaymentEntityDTO dto) {
    PaymentEntity entity = new PaymentEntity();
    entity.setUuid(dto.getUuid());
    entity.setType(dto.getType());
    entity.setOrganisation_id(dto.getOrganisation_id());
    entity.setVersion(dto.getVersion());
    entity.setPaymentAttributes(populatePaymentAttributesEntity(dto.getAttributes()));
    return entity;
  }

  private PaymentAttributes populatePaymentAttributesEntity(PaymentAttributesDTO payAttribDTO) {
    PaymentAttributes paymentAttributes = new PaymentAttributes();

    paymentAttributes.setAmount(Float.valueOf(payAttribDTO.getAmount()));
    paymentAttributes.setCurrency(CurrencyEnum.valueOf(payAttribDTO.getCurrency()));
    paymentAttributes.setEnd_reference(payAttribDTO.getEnd_reference());
    paymentAttributes.setNumeric_ref(Long.valueOf(payAttribDTO.getNumeric_ref()));
    paymentAttributes.setPayment_id(Long.valueOf(payAttribDTO.getPayment_id()));
    paymentAttributes.setPayment_purpose(payAttribDTO.getPayment_purpose());
    paymentAttributes.setPaymentScheme(PaymentSchemeEnum.valueOf(payAttribDTO.getPaymentScheme().toUpperCase()));
    paymentAttributes.setPaymentType(PaymentTypeEnum.valueOf(payAttribDTO.getPaymentType().toUpperCase()));
    paymentAttributes.setProcessingDate(payAttribDTO.getProcessingDate());
    paymentAttributes.setReference(payAttribDTO.getReference());
    paymentAttributes.setSchemePaymentSubType(
        Scheme_Payment_Sub_Type_Enum.valueOf(payAttribDTO.getSchemePaymentSubType().toUpperCase()));
    paymentAttributes.setSchemePaymentType(
        Scheme_Payment_Type_Enum.valueOf(payAttribDTO.getSchementPaymentType().toUpperCase()));

    populatePartyEntity(payAttribDTO.getBeneficiaryParty()).addBeneficiaryPaymentAttributes(paymentAttributes);
    populatePartyEntity(payAttribDTO.getDebtorParty()).addDebtorPaymentAttributes(paymentAttributes);
    populatePartyEntity(payAttribDTO.getSponsorParty()).addSponsorPaymentAttributes(paymentAttributes);
    //paymentAttributes.setSponsorParty(populatePartyEntity(payAttribDTO.getSponsorParty()));

    paymentAttributes.setChargesInformation(
        populateChargesInfoEntity(payAttribDTO.getChargesInformation()));
    paymentAttributes.setForexEntity(populateForexEntity(payAttribDTO.getForex()));

    return paymentAttributes;
  }

  private PartyEntity populatePartyEntity(PartyEntityDTO entityDTO) {
    PartyEntity partyEntity = new PartyEntity();
    if (entityDTO.getAccount_number() != null && entityDTO.getBank_id() != null) {
      partyEntity.setBankIdAccIdKey(
          new BankIdAccIdKeyEntity(entityDTO.getAccount_number(), entityDTO.getBank_id()));
    }
    partyEntity.setAccountName(entityDTO.getAccountName());
    partyEntity.setAccNumberCode(entityDTO.getAccNumberCode());
    if (null != entityDTO.getAccountType()) {
      partyEntity.setAccountType(AccountTypeEnum.getEnumOnOrdinal(entityDTO.getAccountType()));
    }
    partyEntity.setAddress(entityDTO.getAddress());
    partyEntity.setBankIdCode(entityDTO.getBankIdCode());
    partyEntity.setName(entityDTO.getName());
    return partyEntity;
  }

  private ChargesInformationEntity populateChargesInfoEntity(ChargesInformationDTO dto) {

    ChargesInformationEntity chargesInformationEntity = new ChargesInformationEntity();

    chargesInformationEntity.setBearerCode(dto.getBearerCode());
    List<ChargesObject> chargesObjectList =
        dto.getSenderCharges()
            .stream()
            .map(
                chargeto ->
                    new ChargesObject(
                        Float.valueOf(chargeto.getAmount()),
                        CurrencyEnum.valueOf(chargeto.getCurrency())))
            .collect(Collectors.toList());
    chargesInformationEntity.setSenderCharges(chargesObjectList);
    chargesInformationEntity.setReceiverChargesAmt(Float.valueOf(dto.getReceiverChargesAmt()));
    chargesInformationEntity.setReceiverChargesCurrency(
        CurrencyEnum.valueOf(dto.getReceiverChargesCurrency()));
    return chargesInformationEntity;
  }

  private ForexEntity populateForexEntity(ForexDTO dto){
      ForexEntity forexEntity = new ForexEntity();
      forexEntity.setContractReference(dto.getContractReference());
      forexEntity.setExchangeRate(Float.valueOf(dto.getExchangeRate()));
      forexEntity.setOriginalAmount(Float.valueOf(dto.getOriginalAmount()));
      forexEntity.setOriginalCurrency(CurrencyEnum.valueOf(dto.getOriginalCurrency()));
      return forexEntity;
  }

  public PaymentEntityDTO getPaymentEntityDTO(PaymentEntity entity) {

    return null;
  }
}
