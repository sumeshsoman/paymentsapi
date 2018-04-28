package com.form3.payment.service;

import com.form3.payment.dao.IPaymentDao;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.dto.PaymentWrapperDTO;
import com.form3.payment.model.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService implements IPaymentService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private IPaymentDao paymentDao;

  private ModelMapper modelMapper = new ModelMapper();

  @Override
  public PaymentWrapperDTO getAllPayments(int start, int offset) {
    List<PaymentEntity> paymentEntityList = paymentDao.listAllPayments(start, offset);
    List<PaymentEntityDTO> dtos =
        paymentEntityList
            .parallelStream()
            .map(entity -> modelMapper.map(entity, PaymentEntityDTO.class))
            .collect(Collectors.toList());
    PaymentWrapperDTO wrapperDTO = new PaymentWrapperDTO();
    wrapperDTO.setEntityDTOList(dtos);
    return wrapperDTO;
  }

  @Override
  public PaymentWrapperDTO getPaymentsById(String id) {
    PaymentEntity paymentEntity = paymentDao.getPaymentByUuid(id);
    PaymentEntityDTO dto = modelMapper.map(paymentEntity, PaymentEntityDTO.class);
    PaymentWrapperDTO wrapperDTO = new PaymentWrapperDTO();
    wrapperDTO.setEntityDTOList(Arrays.asList(dto));
    return wrapperDTO;
  }

  @Override
  public void createPayment(PaymentWrapperDTO dto) {
    List<PaymentEntityDTO> entityDTOS = dto.getEntityDTOList();
    List<PaymentEntity> paymentEntities = entityDTOS.stream().map(entityDTO -> modelMapper.map(entityDTO, PaymentEntity.class))
            .collect(Collectors.toList());
    paymentEntities.stream().forEach(entity -> paymentDao.createPaymentEntity(entity));
  }

  @Override
  public void updatePayment(PaymentWrapperDTO dto) {
    List<PaymentEntityDTO> entityDTOS = dto.getEntityDTOList();
    List<PaymentEntity> paymentEntities = entityDTOS.stream().map(entityDTO -> modelMapper.map(entityDTO, PaymentEntity.class))
            .collect(Collectors.toList());
    paymentEntities.stream().forEach(entity -> paymentDao.updatePaymentEntity(entity));
  }

  @Override
  public void deletePaymentById(String id) {
    PaymentEntity paymentEntity = paymentDao.getPaymentByUuid(id);
    if (null != paymentEntity) {
      paymentDao.deletePaymentEntity(paymentEntity);
    }
  }
}
