package com.msjava.mscreditassessment.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCreditCard {
    private String creditCard;
    private String brand;
    private BigDecimal ApprovedLimit;
}
