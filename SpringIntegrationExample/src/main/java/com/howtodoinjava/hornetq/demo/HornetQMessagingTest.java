package com.howtodoinjava.hornetq.demo;

import org.springframework.context.ApplicationContext;

public class HornetQMessagingTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//Load configurations 
		ApplicationContext context = SpringContainerLoader.loadEnvironment();
		
		//Start the message producer
		new Thread((Runnable)context.getBean("messageProducerClient")).start();
		
		//Start the message consumer
		new Thread((Runnable)context.getBean("messageConsumerClient")).start();
		
	}
}
