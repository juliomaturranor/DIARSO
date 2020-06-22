package com.jcalzado.demo.transaction;

public class ResponseJSON {
	private String status;
	private String date;
	private String message;
	private Object data;
	
	public ResponseJSON(String status, String date, String message, Object data) {
		super();
		this.status = status;
		this.date = date;
		this.message = message;
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
}
