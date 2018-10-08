package com.example.demo.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer implements Runnable{
    public static short counter=0;
    @Override
    public void run(){

        try {
            /* URL of the JMS server. DEFAULT_BROKER_URL means that JMS server is on localhost. */
            final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

            // Creating connection from ConnectionFactory
            Connection connection = connectionFactory.createConnection();

            //Starting Connection
            connection.start();

            //Creating session from Connection
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //Creating the Queue
            Destination destination = session.createQueue("ThisIsQueueName");

            //Creating a Producer
            MessageProducer producer = session.createProducer(destination);

            //Creating a text message
            TextMessage textMessage = session.createTextMessage("This is message number " + ++counter + "." );

            //Sending message to the queue
            producer.send(textMessage);

            //Terminate session
            session.close();

            //Terminate connection
            connection.close();

        }catch (Exception e){
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}
