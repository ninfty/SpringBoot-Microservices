package com.msjava.mscreditcards.infra.repository;

import com.msjava.mscreditcards.domain.CustomerCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCreditCardRepository extends JpaRepository<CustomerCreditCard, Long> {
    List<CustomerCreditCard> findByDocument(String document);
}
