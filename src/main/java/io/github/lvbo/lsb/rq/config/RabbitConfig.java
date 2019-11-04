package io.github.lvbo.lsb.rq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    public static final String HELLO_QUEUE = "hello_queue";
    public static final String QUEUE_A = "queue_a";
    public static final String QUEUE_B = "queue_b";
    public static final String FANOUT_EXCHANGE_ONE = "fanout_exchange_one";
    public static final String FANOUT_EXCHANGE_TWO = "fanout_exchange_two";
    public static final String DIRECT_EXCHANGE_ONE = "direct_exchange_one";
    public static final String DIRECT_EXCHANGE_TWO = "direct_exchange_two";
    public static final String DIRECT_EXCHANGE_THREE = "direct_exchange_three";
    public static final String QUEUE_TEST_1 = "queue_test_1";
    public static final String QUEUE_TEST_2 = "queue_test_2";
    public static final String QUEUE_TEST_3 = "queue_test_3";
    public static final String QUEUE_TEST_4 = "queue_test_4";
    public static final String ROUTING_1 = "routing_1";
    public static final String ROUTING_2 = "routing_2";
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    public static final String TOPIC_QUEUE_3 = "topic_queue_3";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String MANUAL_ACK_QUEUE = "manual_ack_queue";
    public static final String BRING_HEADER_MESSAGE_QUEUE = "bring_header_message_queue";
    public static final String MY_MSG_HEADER = "my_msg_header";
    public static final String OBJECT_QUEUE = "object_queue";

    /**
     * 此Queue绑定默认的Direct类型的交换器
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(HELLO_QUEUE);
    }

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B);
    }

    @Bean
    public FanoutExchange fanoutExchangeOne() {
        return new FanoutExchange(FANOUT_EXCHANGE_ONE);
    }

    @Bean
    public FanoutExchange fanoutExchangeTwo() {
        return new FanoutExchange(FANOUT_EXCHANGE_TWO);
    }

    @Bean
    public Binding queueABindingFanoutExchangeOne(Queue queueA, FanoutExchange fanoutExchangeOne) {
        return BindingBuilder.bind(queueA).to(fanoutExchangeOne);
    }

    @Bean
    public Binding queueBBindingExchangeOne(Queue queueB, FanoutExchange fanoutExchangeOne) {
        return BindingBuilder.bind(queueB).to(fanoutExchangeOne);
    }

    @Bean
    public Binding queueABindingExchangeTwo(Queue queueA, FanoutExchange fanoutExchangeTwo) {
        return BindingBuilder.bind(queueA).to(fanoutExchangeTwo);
    }

    @Bean
    public DirectExchange directExchangeOne() {
        return new DirectExchange(DIRECT_EXCHANGE_ONE);
    }

    @Bean
    public DirectExchange directExchangeTwo() {
        return new DirectExchange(DIRECT_EXCHANGE_TWO);
    }

    @Bean
    public DirectExchange directExchangeThree() {
        return new DirectExchange(DIRECT_EXCHANGE_THREE);
    }

    @Bean
    public Queue queueTest1() {
        return new Queue(QUEUE_TEST_1);
    }

    @Bean
    public Queue queueTest2() {
        return new Queue(QUEUE_TEST_2);
    }

    @Bean
    public Queue queueTest3() {
        return new Queue(QUEUE_TEST_3);
    }

    @Bean
    public Queue queueTest4() {
        return new Queue(QUEUE_TEST_4);
    }

    @Bean
    public Binding queueTest1BindingToDirectExchangeOne(Queue queueTest1, DirectExchange directExchangeOne) {
        return BindingBuilder.bind(queueTest1).to(directExchangeOne).with(ROUTING_1);
    }

    @Bean
    public Binding queueTest2bindingToDirectExchangeA2(Queue queueTest2, DirectExchange directExchangeOne) {
        return BindingBuilder.bind(queueTest2).to(directExchangeOne).with(ROUTING_1);
    }

    @Bean
    public Binding bindingToDirectExchangeB(Queue queueTest3, DirectExchange directExchangeTwo) {
        return BindingBuilder.bind(queueTest3).to(directExchangeTwo).with(ROUTING_1);
    }

    @Bean
    public Binding bindingToDirectExchangeC(Queue queueTest4, DirectExchange directExchangeThree) {
        return BindingBuilder.bind(queueTest4).to(directExchangeThree).with(ROUTING_2);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2);
    }

    @Bean
    public Queue topicQueue3() {
        return new Queue(TOPIC_QUEUE_3);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicQueue1BindingTopicExchange(Queue topicQueue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.queue.*");
    }

    @Bean
    public Binding topicQueue2BindingTopicExchange(Queue topicQueue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("*.*.two");
    }

    @Bean
    public Binding topicQueue3BindingTopicExchange(Queue topicQueue3, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue3).to(topicExchange).with("topic.#");
    }

    @Bean
    public Queue manualAckQueue() {
        return new Queue(MANUAL_ACK_QUEUE);
    }

    @Bean
    public Queue bringHeaderMessageQueue() {
        return new Queue(BRING_HEADER_MESSAGE_QUEUE);
    }

    @Bean
    public Queue objectQueue() {
        return new Queue(OBJECT_QUEUE);
    }
}
