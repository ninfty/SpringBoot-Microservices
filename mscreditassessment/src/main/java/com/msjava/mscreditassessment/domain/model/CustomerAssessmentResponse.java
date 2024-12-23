package com.msjava.mscreditassessment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerAssessmentResponse {
    private List<ApprovedCreditCard> creditCards;
}
