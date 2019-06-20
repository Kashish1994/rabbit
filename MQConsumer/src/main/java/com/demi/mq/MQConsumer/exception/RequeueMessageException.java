package com.demi.mq.MQConsumer.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;

public class RequeueMessageException extends AmqpRejectAndDontRequeueException {
	public RequeueMessageException(String message) {
		super(message);
	}
}
