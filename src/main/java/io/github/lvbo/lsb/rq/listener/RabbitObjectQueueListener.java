package io.github.lvbo.lsb.rq.listener;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import io.github.lvbo.lsb.rq.domain.MessageObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.OBJECT_QUEUE)
@Slf4j
public class RabbitObjectQueueListener {

    @RabbitHandler
    public void process(MessageObj messageObj) {
        log.info("receiver: {}", messageObj);
    }
}
