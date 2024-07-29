/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.Order;
import xyz.wrywebsite.constant.vo.OrderResponseVo;
import xyz.wrywebsite.seckillweb.service.OrderMessageService;

/**
 * @author wry
 * @version 1.0
 * @classname OrderMessageServiceImpl
 * @description
 * @since 1.0
 */
@Service
@Slf4j
public class OrderMessageServiceImpl implements OrderMessageService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public Long sendOrder(OrderResponseVo orderResponseVo) {
        // 发送下单消息
        amqpTemplate.convertAndSend(Constants.EXCHANGE_ORDER_NAME, Constants.ROUTING_KEY_ORDER_NAME, orderResponseVo);
        log.debug("下单消息已发送，订单 [{}]", orderResponseVo.toString());
        return Constants.RABBIRMQ_RUSILT_SUCCESS;
    }
}
