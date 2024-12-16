package com.msjava.mscreditcards.application;

import com.msjava.mscreditcards.domain.CustomerCreditCard;
import com.msjava.mscreditcards.infra.repository.CustomerCreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCreditCardService {

    private final CustomerCreditCardRepository repository;

    public List<CustomerCreditCard> listCreditCardByDocument(String document) {
        return repository.findByDocument(document);
    }
}
