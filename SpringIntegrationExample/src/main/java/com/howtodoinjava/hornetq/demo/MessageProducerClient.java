package com.howtodoinjava.hornetq.demo;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.ProducerCallback;

public class MessageProducerClient implements Runnable {

	private static Log log = LogFactory.getLog(MessageProducerClient.class);
	
	private static int counter = 1;

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void run() {
		jmsTemplate.execute("ExampleQueue", new ProducerCallback() {
			@Override
			public Object doInJms(Session session, MessageProducer producer)
					throws JMSException {
				while (true) {
					TextMessage msg = session.createTextMessage("Message number : " + counter++);
					log.trace("Sending msg: " + msg);
					producer.send(msg);
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}
}
