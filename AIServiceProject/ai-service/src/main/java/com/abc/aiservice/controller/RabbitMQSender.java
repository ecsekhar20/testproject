package com.abc.aiservice.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.aiservice.constants.Constants;
import com.abc.aiservice.model.InvoiceData;


@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	
	public void send(InvoiceData invoiceData) {
		
		amqpTemplate.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, invoiceData);
		System.out.println("Send msg = " + invoiceData);
	    
	}
}