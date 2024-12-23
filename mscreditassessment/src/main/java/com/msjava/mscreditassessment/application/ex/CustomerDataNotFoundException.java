package com.msjava.mscreditassessment.application.ex;

public class CustomerDataNotFoundException extends Exception {
    public CustomerDataNotFoundException() {
        super("Customer not found");
    }
}
