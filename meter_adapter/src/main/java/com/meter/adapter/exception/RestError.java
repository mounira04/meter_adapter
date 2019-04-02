package com.meter.adapter.exception;

import org.springframework.http.HttpStatus;

public class RestError {

	private int errorCode;
	private String errorMessage;
	private String requestURL;
	private HttpStatus status;

	

	public RestError() {
		super();
		
	}

	public RestError(int errorCode, String errorMessage, String requestURL, HttpStatus status) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.requestURL = requestURL;
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int i) {
		this.errorCode = i;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setRequestURL(String url) {
		this.requestURL = url;

	}

	public String getRequestURL() {
		return requestURL;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}