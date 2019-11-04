package io.github.lvbo.lsb.rq.service;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import io.github.lvbo.lsb.rq.domain.MessageObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitSender implements RabbitTemplate.ReturnCallback, RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.info("消息发送失败 cause: {} data: {}", cause, correlationData.toString());
        } else {
            log.info("消息发送成功 data: {}", correlationData.toString());
        }
    }

    public void sendToHelloQueue(String content) {
        rabbitTemplate.convertAndSend(RabbitConfig.HELLO_QUEUE, content);
    }

    public void send(String exchangeName, String routingKey, String content) {
//        this.rabbitTemplate.setReturnCallback(this::returnedMessage);
//        this.rabbitTemplate.setConfirmCallback(this::confirm);
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, content);
    }

    public void sendObj() {
        MessageObj obj = new MessageObj();
        obj.setAck(false);
        obj.setId(123);
        obj.setName("zhangsan");
        obj.setValue("data");
        System.out.println("发送 : " + obj);
        this.rabbitTemplate.convertAndSend("queueB", obj);
    }
}
