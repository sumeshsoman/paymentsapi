package com.form3.payment.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.form3.payment.dto.PaymentEntityDTO;
import com.form3.payment.dto.PaymentWrapperDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
public class PaymentControllerTest {

  @Resource private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  private RestTemplate restTemplate;

  private ObjectMapper objectMapper = new ObjectMapper();

  private ClassLoader classLoader;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    restTemplate = new RestTemplate();
    IntegrationTestUtil.setTimeout(restTemplate, 5000);
    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    createData();
  }

  @After
  public void tearDown() throws Exception {
    restTemplate = null;
    this.mockMvc = null;
  }

  private void createData() throws Exception {
    classLoader = getClass().getClassLoader();
    File jsonFile = new File(classLoader.getResource("input_data.json").getFile());

    PaymentWrapperDTO data = objectMapper.readValue(jsonFile, PaymentWrapperDTO.class);
    System.out.println("The data going to be created " + data);

    MvcResult result =
        mockMvc
            .perform(
                post(PaymentController.paymentURI)
                    .content(IntegrationTestUtil.convertObjectToJsonBytes(data))
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    Assert.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

  }

  private String getUUIDOfPaymentEntityFromDB() throws Exception {
    MvcResult result = mockMvc.perform(get(PaymentController.paymentURI)).andExpect(status().isOk()).andReturn();

    System.out.println(result.getResponse().getContentAsString());
    PaymentWrapperDTO dto =
            objectMapper.readValue(
                    result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);

    String uuidForTest = dto.getEntityDTOList().get(0).getUuid();
    return uuidForTest;

  }

  @Test
  public void getAllPayments() throws Exception {
    String query = PaymentController.paymentURI;
    MvcResult result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();

    System.out.println(result.getResponse().getContentAsString());
    PaymentWrapperDTO dto =
        objectMapper.readValue(
            result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(2, dto.getEntityDTOList().size());
  }

  @Test
  public void getPaymentById() throws Exception {
    String id = getUUIDOfPaymentEntityFromDB();
    String query = PaymentController.paymentURI + "/" + id;
    MvcResult result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();

    PaymentWrapperDTO dto =
        objectMapper.readValue(
            result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(1, dto.getEntityDTOList().size());
    Assert.assertEquals(id, dto.getEntityDTOList().get(0).getUuid());
  }

  @Test
  public void createPayment() throws Exception {
    classLoader = getClass().getClassLoader();
    File jsonFile = new File(classLoader.getResource("data.json").getFile());

    PaymentWrapperDTO data = objectMapper.readValue(jsonFile, PaymentWrapperDTO.class);

    MvcResult result =
        mockMvc
            .perform(
                post(PaymentController.paymentURI)
                    .content(IntegrationTestUtil.convertObjectToJsonBytes(data))
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    Assert.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());

    int start = 0;
    int count = 10;
    String query = PaymentController.paymentURI + "?start=" + start + "&count=" + count;
    result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();
    PaymentWrapperDTO dto =
        objectMapper.readValue(
            result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(10, dto.getEntityDTOList().size());
  }

  @Test
  public void updatePayment() throws Exception {
    String id = getUUIDOfPaymentEntityFromDB();
    String query = PaymentController.paymentURI + "/" + id;
    MvcResult result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();

    PaymentWrapperDTO dto =
        objectMapper.readValue(
            result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(1, dto.getEntityDTOList().size());
    PaymentEntityDTO paymentEntityDTO = dto.getEntityDTOList().get(0);
    Assert.assertEquals(id, paymentEntityDTO.getUuid());

    String updated_org_id = "3333333";
    paymentEntityDTO.setOrganisation_id(updated_org_id);

    PaymentWrapperDTO data = new PaymentWrapperDTO();
    data.setEntityDTOList(Arrays.asList(paymentEntityDTO));

    result =
        mockMvc
            .perform(patch(PaymentController.paymentURI)
                    .content(IntegrationTestUtil.convertObjectToJsonBytes(data))
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();

    dto =
            objectMapper.readValue(
                    result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(1, dto.getEntityDTOList().size());
    paymentEntityDTO = dto.getEntityDTOList().get(0);
    Assert.assertEquals(id, paymentEntityDTO.getUuid());
    Assert.assertEquals(updated_org_id, paymentEntityDTO.getOrganisation_id());
  }

  @Test
  public void deletePayment() throws Exception{
    String id = getUUIDOfPaymentEntityFromDB();

    String query = PaymentController.paymentURI + "/" + id;

    MvcResult result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();
    Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    PaymentWrapperDTO dto =
            objectMapper.readValue(
                    result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(1, dto.getEntityDTOList().size());

    result = mockMvc.perform(delete(PaymentController.paymentURI +"/{id}", id))
            .andExpect(status().isNoContent())
            .andReturn();
    Assert.assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());

    result = mockMvc.perform(get(query)).andExpect(status().isOk()).andReturn();
    dto =
            objectMapper.readValue(
                    result.getResponse().getContentAsByteArray(), PaymentWrapperDTO.class);
    Assert.assertNotNull(dto);
    Assert.assertEquals(0, dto.getEntityDTOList().size());
  }
}
