package com.example.demo;

import com.example.demo.consumer.Consumer;
import com.example.demo.producer.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendMessageToActivemqApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(SendMessageToActivemqApplication.class, args);

        thread(new Producer(), false);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        Thread.sleep(1000);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        Thread.sleep(1000);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        thread(new Producer(), false);
        Thread.sleep(1000);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        thread(new Producer(), false);
        thread(new Consumer(), false);
        thread(new Consumer(), false);
        thread(new Producer(), false);
    }
    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }

}
