package com.quikr.demo.MQ.producer;

import java.io.Serializable;

public class SmsData implements Serializable{
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
