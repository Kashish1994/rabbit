package com.quikr.demo.MQ.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quikr.demo.MQ.producer.MQProducer;

@RestController("v1/")
public class Controller {

	@Autowired
	private MQProducer producer;

	@GetMapping("/push/sms")
	public String sendMessage(@RequestParam("message") String message) {
		producer.sendMessage(message);
		return "{\"status\":\"success\"}";
	}

}
