package com.msjava.mscustomers.application;

import com.msjava.mscustomers.domain.Customer;
import com.msjava.mscustomers.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    @Transactional
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> getByDocument(String document) {
        return repository.findByDocument(document);
    }
}
