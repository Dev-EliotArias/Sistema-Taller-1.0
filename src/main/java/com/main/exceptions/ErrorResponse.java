package com.main.exceptions;

import lombok.Data;

@Data
public class ErrorResponse {
	
	private String code;
	private String message;
	
	public ErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	
}
