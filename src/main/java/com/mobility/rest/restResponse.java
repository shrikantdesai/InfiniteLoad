package com.mobility.rest;

public class restResponse {
	private boolean success;
	private String message;
	private int count;
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public restResponse(int count, boolean success, String message) {
		this.success = success;
		this.message = message;
		this.count= count;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
