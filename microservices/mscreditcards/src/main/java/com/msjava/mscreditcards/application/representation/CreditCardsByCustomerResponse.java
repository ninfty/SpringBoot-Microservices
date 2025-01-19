package com.msjava.mscreditcards.application.representation;

import com.msjava.mscreditcards.domain.CustomerCreditCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardsByCustomerResponse {

    private String name;
    private String brand;
    private BigDecimal availableLimit;

    public static CreditCardsByCustomerResponse fromModel(CustomerCreditCard model){
        return new CreditCardsByCustomerResponse(
                model.getCreditCard().getName(),
                model.getCreditCard().getBrand().toString(),
                model.getLimit()
        );
    }
}
