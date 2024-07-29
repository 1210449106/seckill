/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.service.impl;

import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.service.PayMessageService;

/**
 * @author wry
 * @version 1.0
 * @classname PayMessageServiceImpl
 * @description
 * @since 1.0
 */
@Service
public class PayMessageServiceImpl implements PayMessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendPayDelayMessage(Integer orderId) {
        rabbitTemplate.convertAndSend(Constants.EXCHANGE_PAY_NAME, Constants.ROUTING_KEY_PAY_NAME, orderId, message1 -> {
            message1.getMessageProperties().setDelay(Constants.QUEUE_PAY_DELAY_TIME);
            return message1;
        });
    }
}
