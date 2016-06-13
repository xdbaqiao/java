
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	private static final int M = 10;

	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		MessageProducer producer;

		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("FirstQueue");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			sendMessage(session, producer);
			session.commit();

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

	public static void sendMessage(Session session, MessageProducer producer) throws JMSException {
		for (int i = 0; i < M; i++) {
			TextMessage message = session.createTextMessage("Message " + i);
			System.out.println("Have Send Message " + i);
			producer.send(message);
		}
	}
}

