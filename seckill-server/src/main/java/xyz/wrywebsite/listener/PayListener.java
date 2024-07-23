/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.listener;

import com.rabbitmq.client.Channel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.entity.Order;
import xyz.wrywebsite.service.OrderService;

import java.io.IOException;
import java.util.Date;

/**
 * @author wry
 * @version 1.0
 * @classname PayListener
 * @description 支付状态监听
 * @since 1.0
 */
@Component
@Slf4j
public class PayListener {

    @Resource
    private OrderService orderService;


    @RabbitListener(queues = {Constants.QUEUE_PAY_NAME})
    public void paySatus(Message message, Channel channel, Integer orderId) {
        log.debug("订单 {} 支付确认延时消息,开始确认支付状态，当前时间{}", orderId, System.currentTimeMillis());
        // 获取消息tag
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            // 查看状态并修改
            Order order = orderService.getById(orderId);
            if (order.getStatus() == 0) {
                order.setStatus(2).setUpdateTime(new Date());
            }
            orderService.updateById(order);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            log.warn("修改订单 {} 的超时未支付状态失败,消息已重新入队,错误原因 {}", orderId, e);
        }
    }
}
