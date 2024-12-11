package com.msjava.mscustomers.application.representation;

import com.msjava.mscustomers.domain.Customer;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    private String document;
    private String name;
    private Integer age;

    public Customer toModel() {
        return new Customer(document, name, age);
    }
}
