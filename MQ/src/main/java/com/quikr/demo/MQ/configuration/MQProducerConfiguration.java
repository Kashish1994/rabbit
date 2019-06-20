package com.quikr.demo.MQ.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQProducerConfiguration {
	public static final String INCOMING_QUEUE = "sms";
	public static final String EXCHANGE_NAME = "sms-exhange";
	public static final String ROUTING_KEY_NAME = "sms-routing-key";
	public static final String DEAD_LETTER_QUEUE_NAME = "sms-dead-letter.queue";
	public static final String DEAD_LETTER_QUEUE_NAME_EXCHANGE = "sms-dead-letter.queue.exchange";


	@Bean
	DirectExchange exchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}

	@Bean
	Queue incomingQueue() {
		return QueueBuilder.durable(INCOMING_QUEUE).withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_NAME).build();
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(incomingQueue()).to(exchange()).with(ROUTING_KEY_NAME);
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(DEAD_LETTER_QUEUE_NAME).build();
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate ampqRabbitATemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
