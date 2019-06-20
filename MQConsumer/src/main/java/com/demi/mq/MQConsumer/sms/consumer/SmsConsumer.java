package com.demi.mq.MQConsumer.sms.consumer;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.demi.mq.MQConsumer.exception.RequeueMessageException;
import com.rabbitmq.client.Channel;

@Service
public class SmsConsumer {

	@RabbitListener(queues = "sms")
	public void receiveSms(final SmsData message, Channel channel) throws AmqpRejectAndDontRequeueException {
		if (message.getMessage().equals("RAD")) {
			System.out.println("Requeing Message");
			throw new RequeueMessageException("Requeuing Message");
		}
		System.out.println("Recieved " + message.getMessage());
		Math.random();
	}
	
}
