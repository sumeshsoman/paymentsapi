package com.form3.payment.modelmapper;

import com.form3.payment.dto.ChargesInformationDTO;
import com.form3.payment.dto.ChargesObjectDTO;
import com.form3.payment.dto.ForexDTO;
import com.form3.payment.dto.PartyEntityDTO;
import com.form3.payment.dto.PaymentAttributesDTO;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.model.ChargesInformationEntity;
import com.form3.payment.model.ForexEntity;
import com.form3.payment.model.PartyEntity;
import com.form3.payment.model.PaymentAttributes;
import com.form3.payment.model.PaymentEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional(Transactional.TxType.REQUIRED)
public class EntityDTOMapper implements IEntityDTOMapper {

  @Override
  public PaymentEntityDTO getPaymentEntityDTO(PaymentEntity entity) {
    PaymentEntityDTO dto = new PaymentEntityDTO();
    dto.setUuid(entity.getUuid());
    dto.setType(entity.getType());
    dto.setOrganisation_id(entity.getOrganisation_id());
    dto.setVersion(entity.getVersion());
    dto.setAttributes(populatePaymentAttributesDTO(entity.getPaymentAttributes()));
    return dto;
  }

  private PaymentAttributesDTO populatePaymentAttributesDTO(PaymentAttributes paymentAttributes) {
    PaymentAttributesDTO dto = new PaymentAttributesDTO();

    dto.setAmount(paymentAttributes.getAmount().toString());
    dto.setCurrency(paymentAttributes.getCurrency().name());
    dto.setEnd_reference(paymentAttributes.getEnd_reference());
    dto.setNumeric_ref(paymentAttributes.getNumeric_ref().toString());
    dto.setPayment_id(paymentAttributes.getPayment_id().toString());
    dto.setPayment_purpose(paymentAttributes.getPayment_purpose());
    dto.setPaymentScheme(paymentAttributes.getPaymentScheme().name());
    dto.setPaymentType(paymentAttributes.getPaymentType().getName());
    dto.setProcessingDate(paymentAttributes.getProcessingDate());
    dto.setReference(paymentAttributes.getReference());
    dto.setSchemePaymentSubType(paymentAttributes.getSchemePaymentSubType().getName());
    dto.setSchemePaymentType(paymentAttributes.getSchemePaymentType().getName());

    dto.setBeneficiaryParty(populatePartyDTO(paymentAttributes.getBeneficiaryParty()));
    dto.setDebtorParty(populatePartyDTO(paymentAttributes.getDebtorParty()));
    dto.setSponsorParty(populatePartyDTO(paymentAttributes.getSponsorParty()));

    dto.setChargesInformation(populateChargesDTO(paymentAttributes.getChargesInformation()));
    dto.setForex(populateForexDTO(paymentAttributes.getForexEntity()));

    return dto;
  }

  private PartyEntityDTO populatePartyDTO(PartyEntity partyEntity) {
    PartyEntityDTO partyEntityDTO = new PartyEntityDTO();

    partyEntityDTO.setAccount_number(partyEntity.getBankIdAccIdKey().getAccount_number());
    partyEntityDTO.setBank_id(partyEntity.getBankIdAccIdKey().getBank_id());

    partyEntityDTO.setAccountName(partyEntity.getAccountName());
    partyEntityDTO.setAccNumberCode(partyEntity.getAccNumberCode());
    if (null != partyEntity.getAccountType()) {
      partyEntityDTO.setAccountType(partyEntity.getAccountType().name());
    }
    partyEntityDTO.setAddress(partyEntity.getAddress());
    partyEntityDTO.setBankIdCode(partyEntity.getBankIdCode());
    partyEntityDTO.setName(partyEntity.getName());

    return partyEntityDTO;
  }

  private ChargesInformationDTO populateChargesDTO(ChargesInformationEntity chargesObject) {
    ChargesInformationDTO dto = new ChargesInformationDTO();

    dto.setBearerCode(chargesObject.getBearerCode());
    List<ChargesObjectDTO> chargesObjectDTOS =
        chargesObject
            .getSenderCharges()
            .stream()
            .map(
                chargeto ->
                    new ChargesObjectDTO(
                        chargeto.getAmount().toString(), chargeto.getCurrency().name()))
            .collect(Collectors.toList());
    dto.setSenderCharges(chargesObjectDTOS);
    dto.setReceiverChargesAmt(chargesObject.getReceiverChargesAmt().toString());
    dto.setReceiverChargesCurrency(chargesObject.getReceiverChargesCurrency().name());

    return dto;
  }

  private ForexDTO populateForexDTO(ForexEntity entity) {
    ForexDTO dto = new ForexDTO();

    dto.setContractReference(entity.getContractReference());
    dto.setExchangeRate(entity.getExchangeRate().toString());
    dto.setOriginalAmount(entity.getOriginalAmount().toString());
    dto.setOriginalCurrency(entity.getOriginalCurrency().name());

    return dto;
  }
}
