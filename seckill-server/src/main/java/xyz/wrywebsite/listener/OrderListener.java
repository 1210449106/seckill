/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.listener;

import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.OrderResponseVo;
import xyz.wrywebsite.entity.Goods;
import xyz.wrywebsite.entity.Order;
import xyz.wrywebsite.rabbitmqCallback.RabbitMQConfirmCallback;
import xyz.wrywebsite.rabbitmqCallback.RabbitMQReturnsCallback;
import xyz.wrywebsite.service.GoodsService;
import xyz.wrywebsite.service.OrderService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wry
 * @version 1.0
 * @classname OrderLinstner
 * @description 监听下单消息
 * @since 1.0
 */
@Component
@Slf4j
//@Transactional
public class OrderListener {

    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RabbitMQConfirmCallback rabbitMQConfirmCallback;
    @Resource
    private RabbitMQReturnsCallback rabbitMQReturnsCallback;

    @PostConstruct
    public void init() {
        // 配置发送者确认模式回调函数
        rabbitTemplate.setConfirmCallback(rabbitMQConfirmCallback);
        rabbitTemplate.setReturnsCallback(rabbitMQReturnsCallback);
    }

    @RabbitListener(queues = {Constants.QUEUE_ORDER_NAME})
    public void submitOrder(OrderResponseVo orderResponseVo, Channel channel, Message message) {
        // 获取消息tag
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            // 接收到消息,获取Goods对象,生成订单，扣减库存
            Goods goods = goodsService.getById(orderResponseVo.getGoodsId());
            Order order = Order.builder()
                    .goodsId(orderResponseVo.getGoodsId())
                    .userId(orderResponseVo.getUserId())
                    .goodsCount(orderResponseVo.getGoodsCount())
                    .goodsPrice(goods.getPrice())
                    .totalPrice(goods.getPrice().multiply(new BigDecimal(orderResponseVo.getGoodsCount())))
                    .status(0)
                    .creatTime(new Date())
                    .build();
            // 插入订单
            boolean rs = orderService.save(order);
            // 扣减库存
            goods.setCount(goods.getCount() -1);
            boolean rs1 = goodsService.updateById(goods);
            if (!(rs && rs1)) {
                throw new Exception();
            }
            channel.basicAck(tag, false);
            // 发送延时消息
            rabbitTemplate.convertAndSend(Constants.EXCHANGE_PAY_NAME, Constants.ROUTING_KEY_PAY_NAME, order.getOrderId(), message1 -> {
                message1.getMessageProperties().setDelay(Constants.QUEUE_PAY_DELAY_TIME);
                return message1;
            });
            log.debug("订单 {} 支付结果确认消息提交成功,当前时间 {}", order.getOrderId(), System.currentTimeMillis());
        } catch (Exception e) {
            // 插入订单失败,处理失败,重新入队
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } finally {
                log.warn("处理消息 {} 提交的订单出现异常,原因为 {} ,已重新入队", tag, e.toString());
            }
        }
    }
}
