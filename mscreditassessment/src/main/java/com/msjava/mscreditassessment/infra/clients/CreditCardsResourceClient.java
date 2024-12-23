package com.msjava.mscreditassessment.infra.clients;

import com.msjava.mscreditassessment.domain.model.CreditCard;
import com.msjava.mscreditassessment.domain.model.CustomerCreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscreditcards", path = "/credit-cards")
public interface CreditCardsResourceClient {

    @GetMapping(params = "document")
    ResponseEntity<List<CustomerCreditCard>> getCreditCardByCustomer(@RequestParam("document") String document);

    @GetMapping(params = "income")
    ResponseEntity<List<CreditCard>> getCreditCardByIncome(@RequestParam("income") Long income);
}
