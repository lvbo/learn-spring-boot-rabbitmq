package com.lvbo.learn.spring.boot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello_queue");
    }

    @Bean
    public Queue queueA() {
        return new Queue("queueA");
    }

    @Bean
    public Queue queueB() {
        return new Queue("queueB");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("ABExchange");
    }

    @Bean
    public FanoutExchange fanoutExchangeC() {
        return new FanoutExchange("CExchange");
    }


    @Bean
    public Binding bindingExchangeA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue queueA, FanoutExchange fanoutExchangeC) {
        return BindingBuilder.bind(queueA).to(fanoutExchangeC);
    }

    @Bean
    public DirectExchange directExchangeA() {
        return new DirectExchange("direct_exchange_a");
    }

    @Bean
    public DirectExchange directExchangeB() {
        return new DirectExchange("direct_exchange_b");
    }

    @Bean
    public DirectExchange directExchangeC() {
        return new DirectExchange("direct_exchange_c");
    }

    @Bean
    public Queue queueTest1() {
        return new Queue("queue_test_1");
    }

    @Bean
    public Queue queueTest2() {
        return new Queue("queue_test_2");
    }

    @Bean
    public Queue queueTest3() {
        return new Queue("queue_test_3");
    }

    @Bean
    public Queue queueTest4() {
        return new Queue("queue_test_4");
    }

    @Bean
    public Binding bindingToDirectExchangeA(Queue queueTest1, DirectExchange directExchangeA) {
        return BindingBuilder.bind(queueTest1).to(directExchangeA).with("routing_1");
    }

    @Bean
    public Binding bindingToDirectExchangeA2(Queue queueTest2, DirectExchange directExchangeA) {
        return BindingBuilder.bind(queueTest2).to(directExchangeA).with("routing_1");
    }

    @Bean
    public Binding bindingToDirectExchangeB(Queue queueTest3, DirectExchange directExchangeB) {
        return BindingBuilder.bind(queueTest3).to(directExchangeB).with("routing_1");
    }

    @Bean
    public Binding bindingToDirectExchangeC(Queue queueTest4, DirectExchange directExchangeC) {
        return BindingBuilder.bind(queueTest4).to(directExchangeC).with("routing_2");
    }
}
