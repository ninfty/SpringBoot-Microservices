package com.msjava.mscustomers.application;

import com.msjava.mscustomers.application.representation.CustomerSaveRequest;
import com.msjava.mscustomers.domain.Customer;
import com.msjava.mscustomers.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomersResource {

    private final CustomerService service;

    @GetMapping
    public String status() {
        log.info("Customers microservice status");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerSaveRequest request) {
        Customer customer = request.toModel();

        service.save(customer);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("document={document}")
                .buildAndExpand(customer.getDocument())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "document")
    public ResponseEntity customerData(@RequestParam("document") String document) {
        var customer = service.getByDocument(document);

        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }
}
