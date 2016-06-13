import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageConsumer consumer;

		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			consumer = session.createConsumer(destination);
			
			while(true){
				TextMessage message = (TextMessage)consumer.receive(500000);
				if (null != message){
					System.out.println("Recevie Message " + message);
				}
				else
					break;
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (null != connection)
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
		}

	}

}

