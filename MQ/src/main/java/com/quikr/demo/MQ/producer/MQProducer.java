package com.quikr.demo.MQ.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quikr.demo.MQ.configuration.MQProducerConfiguration;

@Service
public class MQProducer {
	@Autowired
	private AmqpTemplate ampqRabbitATemplate;
	
	public void sendMessage(final String message) {
		SmsData data = new SmsData();
		data.setMessage(message);
		ampqRabbitATemplate.convertAndSend(MQProducerConfiguration.EXCHANGE_NAME, MQProducerConfiguration.ROUTING_KEY_NAME, data);
	}
}
