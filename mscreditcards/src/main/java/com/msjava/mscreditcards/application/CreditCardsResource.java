package com.msjava.mscreditcards.application;

import com.msjava.mscreditcards.application.representation.CreditCardSaveRequest;
import com.msjava.mscreditcards.application.representation.CreditCardsByCustomerResponse;
import com.msjava.mscreditcards.domain.CreditCard;
import com.msjava.mscreditcards.domain.CustomerCreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("credit-cards")
@RequiredArgsConstructor
public class CreditCardsResource {

    private final CreditCardService creditCardService;
    private final CustomerCreditCardService customerCreditCardService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CreditCardSaveRequest request ) {
        CreditCard creditCard = request.toModel();

        creditCardService.save(creditCard);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<CreditCard>> getCreditCardByIncome(@RequestParam("income") Long income){
        List<CreditCard> list = creditCardService.getCreditCardsIncomeLessThanEqual(income);

        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "document")
    public ResponseEntity<List<CreditCardsByCustomerResponse>> getCreditCardByCustomer(@RequestParam("document") String document) {
        List<CustomerCreditCard> creditCardsList = customerCreditCardService.listCreditCardByDocument(document);

        List<CreditCardsByCustomerResponse> resultList = creditCardsList.stream()
                .map(CreditCardsByCustomerResponse::fromModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultList);
    }
}
