package com.msjava.mscreditassessment.application.ex;

import lombok.Getter;

public class MicroservicesComunicationErrorException extends Exception {

    @Getter
    private Integer status;

    public MicroservicesComunicationErrorException(String message, Integer status) {
        super(message);

        this.status = status;
    }
}
