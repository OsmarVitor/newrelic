package com.example.newrelic.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TransferBadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TransferBadRequestException() {
        super("Transfer cannot be completed.");
    }
}
