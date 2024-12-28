package com.msjava.mscreditassessment.application;

import com.msjava.mscreditassessment.application.ex.CustomerDataNotFoundException;
import com.msjava.mscreditassessment.application.ex.RequestCreditCardErrorException;
import com.msjava.mscreditassessment.application.ex.MicroservicesComunicationErrorException;
import com.msjava.mscreditassessment.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("credit-assessments")
@RequiredArgsConstructor
public class CreditAssessmentController {

    private final CreditAssessmentService creditAssessmentService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "customer-status", params = "document")
    public ResponseEntity checkCustomerStatus(@RequestParam("document") String document) {
        try {
            CustomerStatus customerStatus = creditAssessmentService.getCustomerStatus(document);

            return ResponseEntity.ok(customerStatus);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesComunicationErrorException e) {
            return ResponseEntity
                    .status(HttpStatus.resolve(e.getStatus()))
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity executeAssessment(@RequestBody AssessmentData data) {
        try {
            CustomerAssessmentResponse customerAssessmentResponse = creditAssessmentService
                    .executeAssessment(data.getDocument(), data.getIncome());

            return ResponseEntity.ok(customerAssessmentResponse);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroservicesComunicationErrorException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("request-credit-card")
    public ResponseEntity requestCreditCard(@RequestBody RequestCreditCardData data) {
        try {
            RequestCreditCardProtocol protocol = creditAssessmentService.requestCreditCard(data);

            return ResponseEntity.ok(protocol);
        } catch (RequestCreditCardErrorException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
