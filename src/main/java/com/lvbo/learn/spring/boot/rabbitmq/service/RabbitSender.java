package com.lvbo.learn.spring.boot.rabbitmq.service;

import com.lvbo.learn.spring.boot.rabbitmq.domain.MessageObj;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
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
            System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
        } else {
            System.out.println("HelloSender 消息发送成功 ");
        }

    }

    public void send() {
        String context = "你好现在是 " + new Date() +"";
        System.out.println("HelloSender发送内容 : " + context);
        this.rabbitTemplate.setReturnCallback(this::returnedMessage);
        this.rabbitTemplate.setConfirmCallback(this::confirm);
        this.rabbitTemplate.convertAndSend("queueA", context);
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
