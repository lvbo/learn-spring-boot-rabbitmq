package io.github.lvbo.lsb.rq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "queue_test_4")
public class RabbitQueueTest4Listener {

//    @RabbitHandler
//    public void process(String hello, Channel channel, Message message) throws IOException {
//        //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        System.out.println("header=" + message.getMessageProperties().getHeaders().get("header1"));
//        System.out.println("receiver queue_test_4 success, msg=" + hello);
//    }

    @RabbitHandler
    public void process(@Payload String hello, Channel channel, Message message, @Header("header1") String header) throws IOException {
        //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("header=" + header);
        System.out.println("receiver queue_test_4 success, msg=" + hello);
    }
}
