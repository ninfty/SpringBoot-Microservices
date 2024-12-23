package com.msjava.mscreditassessment.application;

import com.msjava.mscreditassessment.application.ex.CustomerDataNotFoundException;
import com.msjava.mscreditassessment.application.ex.MicroservicesComunicationErrorException;
import com.msjava.mscreditassessment.domain.model.*;
import com.msjava.mscreditassessment.infra.clients.CreditCardsResourceClient;
import com.msjava.mscreditassessment.infra.clients.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAssessmentService {

    private final CustomerResourceClient customersClient;
    private final CreditCardsResourceClient creditCardsClient;

    public CustomerStatus getCustomerStatus(String document) throws CustomerDataNotFoundException, MicroservicesComunicationErrorException {
        try {
            ResponseEntity<CustomerData> customerDataResponse = customersClient.customerData(document);
            ResponseEntity<List<CustomerCreditCard>> creditCardsResponse = creditCardsClient.getCreditCardByCustomer(document);

            return CustomerStatus
                    .builder()
                    .customer(customerDataResponse.getBody())
                    .creditCards(creditCardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }

            throw new MicroservicesComunicationErrorException(e.getMessage(), status);
        }
    }

    public CustomerAssessmentResponse executeAssessment(String document, Long income) throws CustomerDataNotFoundException, MicroservicesComunicationErrorException{
        try{
            ResponseEntity<CustomerData> customerDataResponse = customersClient.customerData(document);
            ResponseEntity<List<CreditCard>> creditCardsResponse = creditCardsClient.getCreditCardByIncome(income);

            List<CreditCard> creditCards = creditCardsResponse.getBody();

            var approvedCreditCardsList = creditCards.stream().map(creditCard -> {

                CustomerData customerData = customerDataResponse.getBody();

                BigDecimal initialLimit = creditCard.getInitialLimit();
                BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());

                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(initialLimit);

                ApprovedCreditCard approved = new ApprovedCreditCard();
                approved.setCreditCard(creditCard.getName());
                approved.setBrand(creditCard.getBrand());
                approved.setApprovedLimit(approvedLimit);

                return approved;
            }).collect(Collectors.toList());

            return new CustomerAssessmentResponse(approvedCreditCardsList);

        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }

            throw new MicroservicesComunicationErrorException(e.getMessage(), status);
        }
    }
}
