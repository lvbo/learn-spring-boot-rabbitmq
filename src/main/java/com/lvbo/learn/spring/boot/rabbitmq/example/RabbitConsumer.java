package com.lvbo.learn.spring.boot.rabbitmq.example;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitConsumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[] {new Address("127.0.0.1", 5672)};
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root123");
        Connection connection = connectionFactory.newConnection(addresses);
        final Channel channel = connection.createChannel();
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recever message:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };

        channel.basicConsume("queue_demo", consumer);

        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
