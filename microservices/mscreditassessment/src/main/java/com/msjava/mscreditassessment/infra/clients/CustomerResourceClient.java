package com.msjava.mscreditassessment.infra.clients;

import com.msjava.mscreditassessment.domain.model.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "document")
    ResponseEntity<CustomerData> customerData(@RequestParam("document") String document);
}
