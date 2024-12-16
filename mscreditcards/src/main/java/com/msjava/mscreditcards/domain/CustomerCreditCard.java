package com.msjava.mscreditcards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class CustomerCreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String document;

    @ManyToOne
    @JoinColumn(name = "id_credit_card")
    private CreditCard creditCard;

    @Column(name = "credit_card_limit")
    private BigDecimal limit;
}
