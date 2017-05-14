package com.howtodoinjava.hornetq.demo;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContainerLoader 
{
	public static FileSystemXmlApplicationContext loadEnvironment()
	{
		final FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("classpath:config/server/spring.xml");
		context.registerShutdownHook();
		context.start();
		
		return context;
	}
}
