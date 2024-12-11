package com.msjava.mscustomers.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String document;

    @Column
    private String name;

    @Column
    private Integer age;

    public Customer(String document, String name, Integer age) {
        this.document = document;
        this.name = name;
        this.age = age;
    }
}
