package io.github.lvbo.lsb.rq.example;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitConsumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses = new Address[] {new Address("127.0.0.1", 5672)};
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        Connection connection = connectionFactory.newConnection(addresses);
        final Channel channel = connection.createChannel();
        channel.basicQos(64);
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recever message:" + new String(body));
                //  给大家演示一下确认和不确认的情况
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume("queue_demo", consumer);

        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
