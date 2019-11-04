package io.github.lvbo.lsb.rq.listener;

import com.rabbitmq.client.Channel;
import io.github.lvbo.lsb.rq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = RabbitConfig.MANUAL_ACK_QUEUE)
@Slf4j
public class RabbitManualAckQueueListener {

    @RabbitHandler
    public void process(String content, Channel channel, Message message) {
        log.info("receive: {}", content);

        try {
            if (content != null && content.contains("fail")) {
                throw new RuntimeException("message fail");
            } else {
                // 告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                log.info("receive {} is success", content);
            }
        } catch (Exception e) {
            log.error("", e);
            // 丢弃这条消息
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            } catch (IOException ex) {
                log.error("", ex);
            }
            log.info("handle {} fail", content);
        }
    }
}
