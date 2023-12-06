package br.com.rabbitmq.recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Worker {
	
	private final static String QUEUE_NAME = "hello";
	  
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Esperando por messagens. Para sair pressione CTRL+C");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
		    String message = new String(delivery.getBody(), "UTF-8");
		    System.out.println(" [x] Recebido '" + message + "'");
		    
		    try {
		    	doWork(message);
		    } catch (InterruptedException e) {
				e.printStackTrace();
			}  finally {
		        System.out.println(" [x] Concluido");
		    }
		    
		};
		
		boolean autoAck = true; // acknowledgment is covered below
		channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
	}
	
	private static void doWork(String task) throws InterruptedException {
	    for (char ch: task.toCharArray()) {
	        if (ch == '.') Thread.sleep(1000);
	    }
	}

}
