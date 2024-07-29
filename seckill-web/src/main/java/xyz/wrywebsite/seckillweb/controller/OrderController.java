/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.HttpResult;
import xyz.wrywebsite.constant.vo.OrderResponseVo;
import xyz.wrywebsite.constant.vo.ResponseEnum;
import xyz.wrywebsite.constant.vo.Goods;
import xyz.wrywebsite.constant.vo.Order;
import xyz.wrywebsite.seckillweb.service.GoodsService;
import xyz.wrywebsite.seckillweb.service.OrderMessageService;
import xyz.wrywebsite.seckillweb.service.OrderService;

/**
 * @author wry
 * @version 1.0
 * @classname OrderController
 * @description 订单Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;



    /**
     * 根据id获取订单
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public HttpResult getOrderById(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        // 返回数据
        return new HttpResult(ResponseEnum.ORDER_SUCCESS, order);
    }

    @GetMapping("/result/{goodsId}/{userId}")
    public HttpResult getOrderResult(@PathVariable("goodsId") Integer goodsId, @PathVariable("userId") Integer userId) {
        Integer orderResult = orderService.getOrderResult(goodsId, userId);
        HttpResult httpResult = null;
        if (orderResult.equals(0)) {
            // 待支付
            httpResult = new HttpResult(ResponseEnum.ORDER_PAY_WAIT, null);
        }
        if (orderResult.equals(1)) {
            // 已经支付
            httpResult = new HttpResult(ResponseEnum.ORDER_PAY_SUCCESS, null);
        }
        if (orderResult.equals(2)) {
            // 支付超时
            httpResult = new HttpResult(ResponseEnum.ORDER_PAY_FAIL, null);
        }
        return httpResult;
    }
}
