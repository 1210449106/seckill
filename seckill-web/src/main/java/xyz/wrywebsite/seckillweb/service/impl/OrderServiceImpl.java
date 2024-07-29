/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.*;
import xyz.wrywebsite.seckillweb.service.GoodsService;
import xyz.wrywebsite.seckillweb.service.OrderService;

import static xyz.wrywebsite.constant.Constants.*;

/**
 * @author wry
 * @version 1.0
 * @classname OrderServiceImpl
 * @description
 * @since 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private GoodsService goodsService;

    @Override
    public Order getOrderById(Integer orderId) {
        // 根据id获取对象
        String orderStr = (String) redisTemplate.opsForValue().get(Constants.REDIS_ORDER + orderId);
        log.debug("orderStr={}", orderStr);
        // 反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            // 获取订单对象
            order = objectMapper.readValue(orderStr, Order.class);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return order;
    }

    /**
     * 获取订单支付结果
     * @param goodsId 商品Id
     * @param userId 用户Id
     * @return 0 待支付，1 已支付,2 支付超时已作废
     */
    @Override
    public Integer getOrderResult(Integer goodsId, Integer userId) {
        Integer rs = Integer.parseInt((String) redisTemplate.opsForValue().get(REDIS_ORDER_SECKILL + goodsId + ":" + userId));
        return rs;
    }
}
