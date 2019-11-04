package io.github.lvbo.lsb.rq.listener;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_1)
@Slf4j
public class RabbitTopicQueue1Listener {

    @RabbitHandler
    public void process(String payload) throws IOException {
        log.info("topic queue 1 receive msg: {}", payload);
    }
}
