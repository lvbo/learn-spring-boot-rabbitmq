package io.github.lvbo.lsb.rq.listener;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.QUEUE_TEST_2)
@Slf4j
public class RabbitQueueTest2Listener {

    @RabbitHandler
    public void process(@Payload String payload) {
        log.info("queue_test_2 receive msg: {}", payload);
    }
}
