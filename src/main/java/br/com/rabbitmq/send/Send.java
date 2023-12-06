package br.com.rabbitmq.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class Send {
	
	  private final static String QUEUE_NAME = "hello";
	  
	  
	  public static void main(String[] argv) throws Exception {
		  
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  
		  try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {		  
			  channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			  
			  String message = "Hello World! Caminha 1!";
			  channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			  
			  System.out.println(" [x] Enviando '" + message + "'");
		  }	      
	  }

}
