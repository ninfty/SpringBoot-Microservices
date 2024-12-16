package com.msjava.mscreditcards.application;

import com.msjava.mscreditcards.domain.CreditCard;
import com.msjava.mscreditcards.infra.repository.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository repository;

    @Transactional
    public CreditCard save(CreditCard creditCard) {
        return repository.save(creditCard);
    }

    public List<CreditCard> getCreditCardsIncomeLessThanEqual(Long income) {
        var incomeBigDecimal = BigDecimal.valueOf(income);

        return repository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
