package com.meter.adapter.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	@Value("${activemq.destination.queue}")
	private String destination;

	@Bean
	public Queue queue() {
		return new ActiveMQQueue(destination);
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerUrl);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {		

		return new JmsTemplate(connectionFactory());
	}
}
