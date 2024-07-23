/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wry
 * @version 1.0
 * @classname PayReturnsCallback
 * @description
 * @since 1.0
 */
@Component
@Slf4j
public class RabbitMQReturnsCallback implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.error("消息 {} 未从Exchange到达Queue,消息信息{}",returned.getMessage().getMessageProperties().getDeliveryTag(), returned.toString());
    }
}
