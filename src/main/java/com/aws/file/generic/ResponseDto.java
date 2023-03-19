package com.aws.file.generic;

public class ResponseDto {
	
	private String message;

	private String statusCode;

	private Object resultObj;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

	public ResponseDto(String message, String statusCode, Object resultObj) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.resultObj = resultObj;
	}

	public ResponseDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + ", statusCode=" + statusCode + ", resultObj=" + resultObj
				+ ", getMessage()=" + getMessage() + ", getStatusCode()=" + getStatusCode() + ", getResultObj()="
				+ getResultObj() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}


}
