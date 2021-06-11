package com.systemplus.spring.exception.handler;

import io.swagger.annotations.ApiModelProperty;

public class ErrorResponse {

	@ApiModelProperty(value="Status code")
	private String status;

	@ApiModelProperty(value="Error message",example="Error messgae")
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "{status=" + status + ", message=" + message + "}";
	}
	
	

}
