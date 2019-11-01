package io.github.lvbo.lsb.rq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello_queue")
public class SpringRabbitListener {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }


}
