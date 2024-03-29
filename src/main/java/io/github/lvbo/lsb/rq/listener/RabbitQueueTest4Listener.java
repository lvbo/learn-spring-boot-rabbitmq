package io.github.lvbo.lsb.rq.listener;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_TEST_4)
@Slf4j
public class RabbitQueueTest4Listener {

    @RabbitHandler
    public void process(@Payload String payload) {
        log.info("queue_test_4 receive msg: {}", payload);
    }
}
