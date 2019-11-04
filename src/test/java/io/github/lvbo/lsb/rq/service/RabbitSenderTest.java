package io.github.lvbo.lsb.rq.service;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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
    public void testSendRouting1() {
//        rabbitTemplate.convertAndSend("direct_exchange_a","routing_1", "test1");
//        rabbitTemplate.convertAndSend("routing_1", "test1");
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("header1", "mm");
                return message;
            }
        };
//        rabbitTemplate.convertAndSend("direct_exchange_c","routing_2", "test1", messagePostProcessor);
        rabbitTemplate.convertAndSend(null, "hello_queue", "test1", messagePostProcessor);
    }

    @Test
    public void sendToHelloQueue() {
        rabbitSender.sendToHelloQueue("Hello World!");
    }

    @Test
    public void sendToFanoutExchangeOne() {
        rabbitSender.send(RabbitConfig.FANOUT_EXCHANGE_ONE, "mmmmmmm", "两个队列都收到了!");
    }

    @Test
    public void sendToFanoutExchangeTwo() {
        rabbitSender.send(RabbitConfig.FANOUT_EXCHANGE_TWO, "", "我只能发给queue_a!");
    }

    @Test
    public void sendToDirectExchangeOne() {
        rabbitSender.send(RabbitConfig.DIRECT_EXCHANGE_ONE, RabbitConfig.ROUTING_1, "1和2接收！");
    }

    @Test
    public void sendToDirectExchangeTwo() {
        rabbitSender.send(RabbitConfig.DIRECT_EXCHANGE_TWO, RabbitConfig.ROUTING_1, "3接收！");
    }

    @Test
    public void sendToDirectExchangeThree() {
        rabbitSender.send(RabbitConfig.DIRECT_EXCHANGE_THREE, RabbitConfig.ROUTING_2, "4接收！");
    }

    @Test
    public void sendToDirectExchangeThreeAndRoutingKeyError() {
        rabbitSender.send(RabbitConfig.DIRECT_EXCHANGE_THREE, "routing_error", "4接收！");
    }

    @Test
    public void sendToTopicExchage1() {
        rabbitSender.send(RabbitConfig.TOPIC_EXCHANGE, "com.queue.one", "1接收!");
    }

    @Test
    public void sendToTopicExchage12() {
        rabbitSender.send(RabbitConfig.TOPIC_EXCHANGE, "com.queue.two", "1 2 接收!");
    }

    @Test
    public void sendToTopicExchage123() {
        rabbitSender.send(RabbitConfig.TOPIC_EXCHANGE, "topic.queue.two", "1 2 3 接收!");
    }
}
