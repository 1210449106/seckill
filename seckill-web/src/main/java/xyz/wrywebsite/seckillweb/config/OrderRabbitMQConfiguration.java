/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.wrywebsite.constant.Constants;

/**
 * @author wry
 * @version 1.0
 * @classname RabbitMQConfiguration
 * @description
 * @since 1.0
 */
@Configuration
public class OrderRabbitMQConfiguration {

    @Bean
    public DirectExchange orderExchange(){
        return new DirectExchange(Constants.EXCHANGE_ORDER_NAME, true, false);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(Constants.QUEUE_ORDER_NAME, true, false, false);
    }

    @Bean
    public Binding orderBinding(DirectExchange orderExchange, Queue orderQueue){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(Constants.ROUTING_KEY_ORDER_NAME);
    }

    /**
     * 使用Jackson转换消息
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
