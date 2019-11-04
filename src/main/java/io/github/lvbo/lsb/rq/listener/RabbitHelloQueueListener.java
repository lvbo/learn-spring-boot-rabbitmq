package io.github.lvbo.lsb.rq.listener;

import io.github.lvbo.lsb.rq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConfig.HELLO_QUEUE)
@Slf4j
public class RabbitHelloQueueListener {

    @RabbitHandler
    public void process(String hello) {
        log.info("Receiver: {}", hello);
    }


}
