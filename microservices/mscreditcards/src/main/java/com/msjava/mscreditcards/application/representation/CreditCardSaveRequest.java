package com.msjava.mscreditcards.application.representation;

import com.msjava.mscreditcards.domain.CardBrand;
import com.msjava.mscreditcards.domain.CreditCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardSaveRequest {

    private String name;
    private CardBrand brand;
    private BigDecimal income;
    private BigDecimal limit;

    public CreditCard toModel() {
        return new CreditCard(name, brand, income, limit);
    }
}
