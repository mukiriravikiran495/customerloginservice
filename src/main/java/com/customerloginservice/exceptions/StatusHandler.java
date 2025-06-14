package com.customerloginservice.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class StatusHandler implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
	String message;
	String error;
	String statusCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
