package com.form3.payment.controller;

import com.form3.payment.dto.PaymentWrapperDTO;
import com.form3.payment.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController  {

    public static final String paymentURI = "/api.test.form3.tech/v1/payments";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IPaymentService paymentService;

    @RequestMapping(value=paymentURI,produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ResponseEntity<PaymentWrapperDTO> getAllPayments(  @RequestParam( value="start", required=false,defaultValue="0") int start,
                                                              @RequestParam(  value="count", required=false,defaultValue="50" ) int count) {
        HttpStatus status = HttpStatus.OK;
        PaymentWrapperDTO wrapperDTO = paymentService.getAllPayments(start, count);
        return new ResponseEntity<>(wrapperDTO, status);
    }

    @RequestMapping(value=paymentURI + "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
    public ResponseEntity<PaymentWrapperDTO> getPaymentById(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.OK;
        PaymentWrapperDTO wrapperDTO = paymentService.getPaymentsById(id);
        if(null == wrapperDTO){
            status = HttpStatus.NOT_FOUND;
            wrapperDTO = new PaymentWrapperDTO();
        }
        return new ResponseEntity<>(wrapperDTO, status);
    }

    @RequestMapping(value=paymentURI ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
    public ResponseEntity createPayment(@RequestBody PaymentWrapperDTO paymentWrapperDTO) {
        HttpStatus status = HttpStatus.CREATED;
        try {
            paymentService.createPayment(paymentWrapperDTO);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
        }
       return new ResponseEntity(status);
    }

    @RequestMapping(value=paymentURI ,produces = MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.PATCH)
    public ResponseEntity updatePayment(@RequestBody PaymentWrapperDTO paymentWrapperDTO) {
        HttpStatus status = HttpStatus.OK;
        try {
            paymentService.updatePayment(paymentWrapperDTO);
        }catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(status);
    }

    @RequestMapping(value=paymentURI + "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.DELETE)
    public ResponseEntity deletePayment(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        try {
            paymentService.deletePaymentById(id);
        }catch (Exception e){
            logger.info("Error deleting payment object with id " + id  + "  " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(status);
    }
}
