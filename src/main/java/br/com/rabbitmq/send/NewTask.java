package br.com.rabbitmq.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class NewTask {
	
	  private final static String QUEUE_NAME = "hello";
	
	  public static void main(String[] argv) throws Exception {
		  
		  ConnectionFactory factory = new ConnectionFactory();
		  factory.setHost("localhost");
		  
		  try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {		  
			  channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			  
			  for(int i = 0; i < 4; i++) {
				String mensagem  = "Uma mensagem de exemplo " + i;
			  	channel.basicPublish("", "hello", null, mensagem.getBytes());
				System.out.println(" [x] Enviando '" + mensagem + "'");
			  }
			  

		  }	      
	  }

}
