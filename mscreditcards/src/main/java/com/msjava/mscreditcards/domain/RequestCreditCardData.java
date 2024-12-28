package com.msjava.mscreditcards.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestCreditCardData {
    private Long idCreditCard;
    private String document;
    private String address;
    private BigDecimal availableLimit;
}
