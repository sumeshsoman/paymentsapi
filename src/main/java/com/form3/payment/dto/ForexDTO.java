package com.form3.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ForexDTO implements Serializable {

    private static final long serialVersionUID = 1082383225687649906L;

    @JsonProperty("contract_reference")
    private String contractReference;

    @JsonProperty("exchange_rate")
    private String exchangeRate;

    @JsonProperty("original_amount")
    private String originalAmount;

    @JsonProperty("original_currency")
    private String originalCurrency;

    public String getContractReference() {
        return contractReference;
    }

    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }
}
