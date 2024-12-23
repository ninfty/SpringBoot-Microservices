package com.msjava.mscreditassessment.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCreditCard {
    private String name;
    private String brand;
    private BigDecimal availableLimit;
}
