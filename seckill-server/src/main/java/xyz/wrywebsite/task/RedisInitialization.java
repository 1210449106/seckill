/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.task;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.entity.Goods;
import xyz.wrywebsite.entity.Order;
import xyz.wrywebsite.service.GoodsService;
import xyz.wrywebsite.service.OrderService;

import java.util.List;

/**
 * @author wry
 * @version 1.0
 * @classname RedisInitialization
 * @description
 * @since 1.0
 */
@Component
@Slf4j
public class RedisInitialization {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 每3s同步商品列表、库存消息到Redis
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void initListGoods() {
        List<Goods> list = goodsService.list();
        list.stream().forEach(goods -> {
            // 同步商品列表
            redisTemplate.opsForValue().set(Constants.REDIS_GOODS_LIST + goods.getGoodsId(), goods);
            // 同步库存消息
            redisTemplate.opsForValue().setIfAbsent(Constants.REDIS_GOODS_COUNT + goods.getGoodsId(), goods.getCount());
        });
    }

    /**
     * 每3s同步订单、支付状态到Redis
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void initOrders() {
        // 获取所有订单
        List<Order> orderList = orderService.list();
        // 写入Redis
        orderList.stream().forEach(order -> {
            // 写入订单信息
            redisTemplate.opsForValue().set(Constants.REDIS_ORDER + order.getOrderId(), order);
            // 写入订单支付结果
            redisTemplate.opsForValue().set(Constants.REDIS_ORDER_PAY + order.getOrderId(), order.getStatus());
        });
    }


}
