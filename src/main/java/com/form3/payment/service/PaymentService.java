package com.form3.payment.service;

import com.form3.payment.dao.IPaymentDao;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.dto.PaymentWrapperDTO;
import com.form3.payment.model.PaymentEntity;
import com.form3.payment.modelmapper.IDTOEntityMapper;
import com.form3.payment.modelmapper.IEntityDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService implements IPaymentService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private IPaymentDao paymentDao;

  @Autowired private IEntityDTOMapper entityToDTOMapper;

  @Autowired private IDTOEntityMapper dtoToEntityMapper;

  @Override
  public PaymentWrapperDTO getAllPayments(int start, int offset) {
    PaymentWrapperDTO wrapperDTO = new PaymentWrapperDTO();
    try {
      List<PaymentEntity> paymentEntityList = paymentDao.listAllPayments(start, offset);
      List<PaymentEntityDTO> dtos =
          paymentEntityList
              .stream()
              .map(entity -> entityToDTOMapper.getPaymentEntityDTO(entity))
              .collect(Collectors.toList());

      wrapperDTO.setEntityDTOList(dtos);
    } catch (NoResultException e) {
      logger.warn("No Payment Entities found in DB ");
    }
    return wrapperDTO;
  }

  @Override
  public PaymentWrapperDTO getPaymentsById(String id) {
    PaymentWrapperDTO wrapperDTO = null;
    try {
      wrapperDTO = new PaymentWrapperDTO();
      PaymentEntity paymentEntity = paymentDao.getPaymentByUuid(id);
      PaymentEntityDTO dto = entityToDTOMapper.getPaymentEntityDTO(paymentEntity);
      wrapperDTO.setEntityDTOList(Arrays.asList(dto));
    } catch (NoResultException e) {
      logger.warn("No Payment Entity found with id " + id);
    }
    return wrapperDTO;
  }

  @Override
  public void createPayment(PaymentWrapperDTO dto) {
    List<PaymentEntityDTO> entityDTOS = dto.getEntityDTOList();
    List<PaymentEntity> paymentEntities =
        entityDTOS
            .stream()
            .map(entityDTO -> dtoToEntityMapper.populatePaymentEntity(entityDTO, null))
            .collect(Collectors.toList());
    paymentEntities.stream().forEach(entity -> paymentDao.createPaymentEntity(entity));
  }

  @Override
  public void updatePayment(PaymentWrapperDTO dto) {
    List<PaymentEntityDTO> entityDTOS = dto.getEntityDTOList();
    List<PaymentEntity> paymentEntities =
        entityDTOS
            .stream()
            .filter(entityDTO -> findValidPaymentEntities(entityDTO) != null)
            .map(
                entityDTO ->
                    dtoToEntityMapper.populatePaymentEntity(
                        entityDTO, findValidPaymentEntities(entityDTO)))
            .collect(Collectors.toList());
    paymentEntities.stream().forEach(entity -> paymentDao.updatePaymentEntity(entity));
  }

  private PaymentEntity findValidPaymentEntities(PaymentEntityDTO dto) {
    PaymentEntity entity = null;
    try {
      entity = paymentDao.getPaymentByUuid(dto.getUuid());
    } catch (NoResultException e) {
      logger.warn("Cannot locate entity with uuid " + dto.getUuid());
    }
    return entity;
  }

  @Override
  public void deletePaymentById(String id) {
    try {
      PaymentEntity paymentEntity = paymentDao.getPaymentByUuid(id);
      if (null != paymentEntity) {
        paymentDao.deletePaymentEntity(paymentEntity);
        System.out.println("##### Successfully deleted !!!!");
      }
    } catch (NoResultException e) {
      logger.warn("No Payment Entity found with id " + id);
      System.out.println("###### No Payment Entity found with id " + id);
    }
  }
}
