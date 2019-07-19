package com.lvbo.learn.spring.boot.rabbitmq.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitSenderTest {

    @Autowired
    private RabbitSender rabbitSender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsg() {
        rabbitSender.send();
    }

    @Test
    public void testSendRouting1() {
//        rabbitTemplate.convertAndSend("direct_exchange_a","routing_1", "test1");
//        rabbitTemplate.convertAndSend("routing_1", "test1");
        rabbitTemplate.convertAndSend("direct_exchange_c","routing_2", "test1");
    }
}
