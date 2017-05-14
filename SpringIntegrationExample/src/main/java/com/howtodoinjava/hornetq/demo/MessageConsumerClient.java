package com.howtodoinjava.hornetq.demo;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

public class MessageConsumerClient implements Runnable {

	private static Log log = LogFactory.getLog(MessageConsumerClient.class);

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void run()
	{
		log.info("Message Consumer is just started !!");
		
		boolean startConn = true;
		jmsTemplate.execute(new SessionCallback<Object>() {
			@Override
			public Object doInJms(Session session) throws JMSException {
				Queue queue = session.createQueue("ExampleQueue");
				MessageConsumer consumer = session.createConsumer(queue);
				while (true) {
					Message msg = consumer.receive();
					log.trace("Received msg: " + msg);
				}
			}
		}, startConn);
	}
}
