package com.main.exceptions;


import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String code;
    private HttpStatus status;

    public CustomException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
