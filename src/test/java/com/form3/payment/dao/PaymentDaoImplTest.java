package com.form3.payment.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.dto.PaymentWrapperDTO;
import com.form3.payment.model.PaymentEntity;
import com.form3.payment.modelmapper.DTOEntityMapper;
import com.form3.payment.modelmapper.PaymentAttributesDTOMap;
import com.form3.payment.modelmapper.PaymentDTOMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
//@Transactional
public class PaymentDaoImplTest {

  @Autowired private IPaymentDao IPaymentDao;

  @Autowired
  private DTOEntityMapper dtoEntityMapper;

  @Before
  public void setUp() throws Exception {
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setOrganisation_id("1");
    paymentEntity.setUuid(UUID.randomUUID().toString());
    IPaymentDao.createPaymentEntity(paymentEntity);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void listAllPayments() {
    assertEquals(1, IPaymentDao.listAllPayments(0, 10).size());
    assertEquals("1", IPaymentDao.listAllPayments(0, 10).get(0).getOrganisation_id());
  }

  @Test
  @Rollback
  public void getPaymentByUuid() {
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setOrganisation_id("2");
    String uuid = UUID.randomUUID().toString();
    paymentEntity.setUuid(uuid);
    IPaymentDao.createPaymentEntity(paymentEntity);

    PaymentEntity paymentEntityFromDB = IPaymentDao.getPaymentByUuid(uuid);
    assertEquals(uuid, paymentEntityFromDB.getUuid());
  }

  @Test
  @Rollback
  public void getPaymentById() {
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setOrganisation_id("3");
    String uuid = UUID.randomUUID().toString();
    paymentEntity.setUuid(uuid);
    IPaymentDao.createPaymentEntity(paymentEntity);

    PaymentEntity paymentEntityFromDB = IPaymentDao.getPaymentByUuid(uuid);
    assertEquals(uuid, paymentEntityFromDB.getUuid());

    PaymentEntity paymentEntityFromDBUsingId =
        IPaymentDao.getPaymentById(paymentEntityFromDB.getId());
    assertEquals(uuid, paymentEntityFromDBUsingId.getUuid());
  }

  @Test
  public void createPaymentEntity() throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    ClassLoader classLoader = getClass().getClassLoader();
    File jsonFile = new File(classLoader.getResource("data.json").getFile());

    //ModelMapper modelMapper = new ModelMapper();
    PaymentWrapperDTO data = mapper.readValue(jsonFile, PaymentWrapperDTO.class);
    List<PaymentEntityDTO> entityDTOS = data.getEntityDTOList();
    //modelMapper.addMappings(new PaymentAttributesDTOMap());
    //modelMapper.addMappings(new PaymentDTOMap());


   /* PaymentEntityDTO paymentEntityDTO = entityDTOS.get(0);
    PaymentEntity paymentEntity =  dtoEntityMapper.populatePaymentEntity(paymentEntityDTO);
    IPaymentDao.createPaymentEntity(paymentEntity);*/

    List<PaymentEntity> paymentEntities =
        entityDTOS
            .stream()
            .map(entityDTO -> dtoEntityMapper.populatePaymentEntity(entityDTO))
            .collect(Collectors.toList());
    paymentEntities.stream().forEach(entity -> IPaymentDao.createPaymentEntity(entity));

  }

  @Test
  @Rollback
  public void deletePaymentEntity() {
    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setOrganisation_id("3");
    String uuid = UUID.randomUUID().toString();
    paymentEntity.setUuid(uuid);
    IPaymentDao.createPaymentEntity(paymentEntity);

    PaymentEntity paymentEntityFromDB = IPaymentDao.getPaymentByUuid(uuid);
    assertEquals(uuid, paymentEntityFromDB.getUuid());

    IPaymentDao.deletePaymentEntity(paymentEntityFromDB);

    try {
      IPaymentDao.getPaymentByUuid(uuid);
      fail("Expect NoResultException");
    } catch (NoResultException e) {
      assertEquals("No entity found for query", e.getMessage());
    }
  }
}
