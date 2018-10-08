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

            // Getting JMS connection from the server and starting it
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);

            // Creating connection from ConnectionFactory
            Connection connection = connectionFactory.createConnection();

            //Starting Connection
            connection.start();
            // JMS messages are sent and received using a Session. We will
            // create here a non-transactional session object. If you want
            // to use transactions you should set the first parameter to 'true'

            //Creating session from Connection
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Destination represents here our queue 'ThisIsQueueName' on the
            // JMS server. You don't have to do anything special on the
            // server to create it, it will be created automatically.
            Destination destination = session.createQueue("ThisIsQueueName");
            // You can create any/many queue names as per your requirement.

            //Creating a Producer
            MessageProducer producer = session.createProducer(destination);
            // MessageProducer is used for sending messages (as opposed
            // to MessageConsumer which is used for receiving them)

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
