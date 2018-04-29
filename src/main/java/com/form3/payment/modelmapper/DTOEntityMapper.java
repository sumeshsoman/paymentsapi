package com.form3.payment.modelmapper;

import com.form3.payment.dao.IPartyDao;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(dontRollbackOn = NoResultException.class)
public class DTOEntityMapper implements IDTOEntityMapper {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private IPartyDao partyDao;

  @Override
  public PaymentEntity populatePaymentEntity(PaymentEntityDTO dto, PaymentEntity passedEntity) {
    PaymentEntity entity = null != passedEntity ? passedEntity : new PaymentEntity();
    entity.setUuid(dto.getUuid());
    entity.setType(dto.getType());
    entity.setOrganisation_id(dto.getOrganisation_id());
    entity.setVersion(dto.getVersion());
    entity.setPaymentAttributes(populatePaymentAttributesEntity(dto.getAttributes(), entity));
    return entity;
  }

  private PaymentAttributes populatePaymentAttributesEntity(
      PaymentAttributesDTO payAttribDTO, PaymentEntity passedEntity) {

    PaymentAttributes paymentAttributes =
        null != passedEntity.getPaymentAttributes()
            ? passedEntity.getPaymentAttributes()
            : new PaymentAttributes();

    paymentAttributes.setAmount(Float.valueOf(payAttribDTO.getAmount()));
    paymentAttributes.setCurrency(CurrencyEnum.valueOf(payAttribDTO.getCurrency()));
    paymentAttributes.setEnd_reference(payAttribDTO.getEnd_reference());
    paymentAttributes.setNumeric_ref(Long.valueOf(payAttribDTO.getNumeric_ref()));
    paymentAttributes.setPayment_id(Long.valueOf(payAttribDTO.getPayment_id()));
    paymentAttributes.setPayment_purpose(payAttribDTO.getPayment_purpose());
    paymentAttributes.setPaymentScheme(
        PaymentSchemeEnum.valueOf(payAttribDTO.getPaymentScheme().toUpperCase()));
    paymentAttributes.setPaymentType(
        PaymentTypeEnum.valueOf(payAttribDTO.getPaymentType().toUpperCase()));
    paymentAttributes.setProcessingDate(payAttribDTO.getProcessingDate());
    paymentAttributes.setReference(payAttribDTO.getReference());
    paymentAttributes.setSchemePaymentSubType(
        Scheme_Payment_Sub_Type_Enum.valueOf(payAttribDTO.getSchemePaymentSubType().toUpperCase()));
    paymentAttributes.setSchemePaymentType(
        Scheme_Payment_Type_Enum.valueOf(payAttribDTO.getSchemePaymentType().toUpperCase()));

    populatePartyEntity(payAttribDTO.getBeneficiaryParty())
        .addBeneficiaryPaymentAttributes(paymentAttributes);
    populatePartyEntity(payAttribDTO.getDebtorParty())
        .addDebtorPaymentAttributes(paymentAttributes);
    populatePartyEntity(payAttribDTO.getSponsorParty())
        .addSponsorPaymentAttributes(paymentAttributes);

    paymentAttributes.setChargesInformation(
        populateChargesInfoEntity(
            payAttribDTO.getChargesInformation(), paymentAttributes.getChargesInformation()));
    paymentAttributes.setForexEntity(
        populateForexEntity(payAttribDTO.getForex(), paymentAttributes.getForexEntity()));

    return paymentAttributes;
  }

  private PartyEntity populatePartyEntity(PartyEntityDTO entityDTO) {
    PartyEntity partyEntity = null;

    try {
      partyEntity = partyDao.getParty(entityDTO.getAccount_number(), entityDTO.getBank_id());
      partyEntity.getBeneficiaryPaymentAttributes();
      partyEntity.getDebtorPaymentAttributes();
      partyEntity.getSponsorPaymentAttributes();
    } catch (NoResultException e) {
      logger.info(
          "Cannot find party " + entityDTO.getAccount_number() + ":" + entityDTO.getBank_id());
    }

    if (null == partyEntity) {
      partyEntity = new PartyEntity();
      if (entityDTO.getAccount_number() != null && entityDTO.getBank_id() != null) {
        partyEntity.setBankIdAccIdKey(
            new BankIdAccIdKeyEntity(entityDTO.getAccount_number(), entityDTO.getBank_id()));
      }
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

  private ChargesInformationEntity populateChargesInfoEntity(
      ChargesInformationDTO dto, ChargesInformationEntity passedEntity) {

    ChargesInformationEntity chargesInformationEntity =
        passedEntity != null ? passedEntity : new ChargesInformationEntity();

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

  private ForexEntity populateForexEntity(ForexDTO dto, ForexEntity passedEntity) {
    ForexEntity forexEntity = passedEntity != null ? passedEntity : new ForexEntity();
    forexEntity.setContractReference(dto.getContractReference());
    forexEntity.setExchangeRate(Float.valueOf(dto.getExchangeRate()));
    forexEntity.setOriginalAmount(Float.valueOf(dto.getOriginalAmount()));
    forexEntity.setOriginalCurrency(CurrencyEnum.valueOf(dto.getOriginalCurrency()));
    return forexEntity;
  }
}
