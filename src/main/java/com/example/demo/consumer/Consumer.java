package com.example.demo.consumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer implements Runnable{


    @Override
    public void run() {
        try {
            //Setting localhost URL for ActiveMQ Connection
            final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

            Connection connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("ThisIsQueueName");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(1000);

            if (message instanceof TextMessage){

                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received Message: "+text);
            }else{
                System.out.println("Received: " +message);
            }
            consumer.close();
            session.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
