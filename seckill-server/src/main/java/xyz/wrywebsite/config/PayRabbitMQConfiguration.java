/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.wrywebsite.constant.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wry
 * @version 1.0
 * @classname PayRabbitMQConfiguration
 * @description 支付状态修改
 * @since 1.0
 */
@Configuration
public class PayRabbitMQConfiguration {

    @Bean
    public CustomExchange payExchange(){
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(Constants.EXCHANGE_PAY_NAME, "x-delayed-message", true, false, arguments);
    }

    @Bean
    public Queue payQueue(){
        return new Queue(Constants.QUEUE_PAY_NAME, true);
    }

    @Bean
    public Binding payBinding(CustomExchange payExchange, Queue payQueue){
        return BindingBuilder
                .bind(payQueue)
                .to(payExchange)
                .with(Constants.ROUTING_KEY_PAY_NAME)
                .noargs();
    }
}
