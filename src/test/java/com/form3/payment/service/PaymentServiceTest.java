package com.form3.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.dto.PaymentWrapperDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
public class PaymentServiceTest {

  @Autowired private IPaymentService paymentService;

  private ObjectMapper mapper;
  private ClassLoader classLoader;

  @Before
  public void setUp() throws Exception {
    mapper = new ObjectMapper();
    classLoader = getClass().getClassLoader();
    File jsonFile = new File(classLoader.getResource("input_data.json").getFile());

    PaymentWrapperDTO data = mapper.readValue(jsonFile, PaymentWrapperDTO.class);
    paymentService.createPayment(data);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void getAllPayments() {
    assertEquals(2, paymentService.getAllPayments(0, 10).getEntityDTOList().size());
  }

  @Test
  public void getPaymentsById() {
    String uuid = paymentService.getAllPayments(0, 10).getEntityDTOList().get(0).getUuid();
    assertEquals(1, paymentService.getPaymentsById(uuid).getEntityDTOList().size());
  }

  @Test
  public void createPayment() throws IOException {
    mapper = new ObjectMapper();
    classLoader = getClass().getClassLoader();
    File jsonFile = new File(classLoader.getResource("data.json").getFile());

    PaymentWrapperDTO data = mapper.readValue(jsonFile, PaymentWrapperDTO.class);
    paymentService.createPayment(data);

    assertEquals(8, paymentService.getAllPayments(0, 8).getEntityDTOList().size());
  }

  @Test
  public void updatePayment() {
    PaymentEntityDTO dto = paymentService.getAllPayments(0, 10).getEntityDTOList().get(0);
    String uuid = dto.getUuid();
    String new_org_id = "3334444";
    dto.setOrganisation_id(new_org_id);

    PaymentWrapperDTO wrapperDTO = new PaymentWrapperDTO();
    wrapperDTO.setEntityDTOList(Arrays.asList(dto));
    paymentService.updatePayment(wrapperDTO);

    PaymentEntityDTO resultDTO = paymentService.getPaymentsById(uuid).getEntityDTOList().get(0);
    assertEquals(
            uuid,
            resultDTO.getUuid());
    assertEquals(
        new_org_id,
            resultDTO.getOrganisation_id());

  }

  @Test
  public void updateMissingPayment() {
    PaymentEntityDTO dto = paymentService.getAllPayments(0, 10).getEntityDTOList().get(0);
    String uuid = UUID.randomUUID().toString();
    String new_org_id = "3334444";
    dto.setOrganisation_id(new_org_id);
    dto.setUuid(uuid); //change primary key

    PaymentWrapperDTO wrapperDTO = new PaymentWrapperDTO();
    wrapperDTO.setEntityDTOList(Arrays.asList(dto));
    paymentService.updatePayment(wrapperDTO);

    assertEquals(0, paymentService.getPaymentsById(uuid).getEntityDTOList().size());
  }

  @Test
  public void deletePaymentById() {
    assertEquals(2, paymentService.getAllPayments(0,10).getEntityDTOList().size());
    String uuid = paymentService.getAllPayments(0, 10).getEntityDTOList().get(0).getUuid();
    assertNotNull(uuid);

    paymentService.deletePaymentById(uuid);
    assertEquals(1, paymentService.getAllPayments(0,10).getEntityDTOList().size());
  }
}
