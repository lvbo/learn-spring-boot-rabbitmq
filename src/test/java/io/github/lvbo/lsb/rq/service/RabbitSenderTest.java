package io.github.lvbo.lsb.rq.service;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void sendToManualAckQueueSuccess() {
        rabbitSender.send("", RabbitConfig.MANUAL_ACK_QUEUE, "给我处理一下啊!");
    }

    @Test
    public void sendToManualAckQueueFail() {
        rabbitSender.send("", RabbitConfig.MANUAL_ACK_QUEUE, "我是一条fail消息!");
    }

    @Test
    public void sendToBringHeaderMsg() {
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setHeader(RabbitConfig.MY_MSG_HEADER, "同学是我呀！");
            return message;
        };
        rabbitTemplate.convertAndSend("", RabbitConfig.BRING_HEADER_MESSAGE_QUEUE, "test1", messagePostProcessor);
    }

    @Test
    public void sendObj() {
        rabbitSender.sendObj();
    }

    @Test
    public void sendToHelloQueueWithConfirmReturn() {
        rabbitSender.sendAndConfirmReturn("", RabbitConfig.HELLO_QUEUE, "Hello World!");
    }

    @Test
    public void sendToMistakeQueueWithConfirmReturn() {
        rabbitSender.sendAndConfirmReturn("", "mistake_queue", "Hello World!");
    }
}
