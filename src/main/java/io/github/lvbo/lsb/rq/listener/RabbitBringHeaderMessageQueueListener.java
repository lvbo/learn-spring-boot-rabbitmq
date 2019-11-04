package io.github.lvbo.lsb.rq.listener;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.BRING_HEADER_MESSAGE_QUEUE)
@Slf4j
public class RabbitBringHeaderMessageQueueListener {

    @RabbitHandler
    public void process(@Payload String content, @Header(RabbitConfig.MY_MSG_HEADER) String msgHeader) {
        log.info("receiver content: {} header: {}", content, msgHeader);
    }
}
