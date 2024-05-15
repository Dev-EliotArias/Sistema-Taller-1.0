package com.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cliente no Encontrado")
public class ClienteNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClienteNotFoundException(String message) {
        super(message);
    }
}