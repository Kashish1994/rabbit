package com.demi.mq.MQConsumer.sms.consumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class MQConsumerConfiguration {

	public static final String DEAD_LETTER_QUEUE_NAME = "sms-dead-letter.queue";
	public static final String INCOMING_QUEUE = "sms";

	@Bean
	Queue incomingQueue() {
		return QueueBuilder.durable(INCOMING_QUEUE).withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_NAME).build();
	}

	@Bean
	public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	/*
	 * public static final String queueName = "sms"; public static final String
	 * exchange = "smsExchange"; public static final String routingkey = "sms";
	 * 
	 * @Bean Queue queue() { return new Queue(queueName, true); }
	 * 
	 * @Bean DirectExchange exchange() { return new DirectExchange(exchange); }
	 * 
	 * @Bean Binding binding(Queue queue, DirectExchange exchange) { return
	 * BindingBuilder.bind(queue).to(exchange).with(routingkey); }
	 * 
	 * @Bean public MessageConverter jsonMessageConverter() { return new
	 * Jackson2JsonMessageConverter(); }
	 * 
	 * @Bean public AmqpTemplate ampqRabbitATemplate(ConnectionFactory
	 * connectionFactory) throws Exception { final RabbitTemplate rabbitTemplate =
	 * new RabbitTemplate(connectionFactory);
	 * rabbitTemplate.setMessageConverter(jsonMessageConverter()); return
	 * rabbitTemplate; }
	 * 
	 * 
	 * @Bean public MappingJackson2MessageConverter
	 * consumerJackson2MessageConverter() { return new
	 * MappingJackson2MessageConverter(); }
	 * 
	 * @Bean public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory()
	 * { DefaultMessageHandlerMethodFactory factory = new
	 * DefaultMessageHandlerMethodFactory();
	 * factory.setMessageConverter(consumerJackson2MessageConverter()); return
	 * factory; }
	 */
}
