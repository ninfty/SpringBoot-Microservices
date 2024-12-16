package com.msjava.mscreditcards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CardBrand brand;

    private BigDecimal income;

    private BigDecimal initialLimit;

    public CreditCard(String name, CardBrand brand, BigDecimal income, BigDecimal initialLimit) {
        this.name = name;
        this.brand = brand;
        this.income = income;
        this.initialLimit = initialLimit;
    }
}
