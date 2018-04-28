package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PaymentWrapperDTO implements Serializable {

    private static final long serialVersionUID = 544210804802634208L;

    @JsonProperty("data")
    private List<PaymentEntityDTO> entityDTOList;

    @JsonProperty("links")
    private PaymentLinksDTO paymentLinks;

    public List<PaymentEntityDTO> getEntityDTOList() {
        return entityDTOList;
    }

    public void setEntityDTOList(List<PaymentEntityDTO> entityDTOList) {
        this.entityDTOList = entityDTOList;
    }

    public PaymentLinksDTO getPaymentLinks() {
        return paymentLinks;
    }

    public void setPaymentLinks(PaymentLinksDTO paymentLinks) {
        this.paymentLinks = paymentLinks;
    }
}
