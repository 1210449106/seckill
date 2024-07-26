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

/**
 * @author wry
 * @version 1.0
 * @classname OrderController
 * @description 订单Controller
 * @since 1.0
 */
@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 获取订单
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public HttpResult getOrderById(@PathVariable("orderId") Integer orderId) {
        // 根据id获取对象
        String orderStr = (String) redisTemplate.opsForValue().get(Constants.REDIS_ORDER + ":" + orderId);
        // 反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            // 获取订单对象
            order = objectMapper.readValue(orderStr, Order.class);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        // 返回数据
        return new HttpResult(ResponseEnum.ORDER_SUCCESS, order);
    }

    @PostMapping("/{randomNum}")
    public HttpResult saveOrder(@PathVariable("randomNum") String randomNum, @RequestBody OrderResponseVo orderResponseVo){
        // 用户是否重复下单
        Boolean rs = redisTemplate.hasKey(Constants.REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId());
        HttpResult httpResult = null;
        if (!rs) {
            // 首次下单
            // 获取目标商品信息
            String goodsStr = (String) redisTemplate.opsForValue().get(Constants.REDIS_GOODS_LIST + orderResponseVo.getGoodsId());
            ObjectMapper objectMapper = new ObjectMapper();
            Goods goods = null;
            try {
                goods = objectMapper.readValue(goodsStr, Goods.class);
            } catch (Exception e) {
                log.warn(e.toString());
            }
            // 核对randomNum
            if (goods.getRandomNum().equals(randomNum)) {
                // 随机码正确,判断库存
                // 获取商品库存
                Integer count = Integer.valueOf((String)redisTemplate.opsForValue().get(Constants.REDIS_GOODS_COUNT + orderResponseVo.getGoodsId()));
                if (count > orderResponseVo.getGoodsCount()) {
                    // 库存充足
                    // redis中扣减库存
                    redisTemplate.opsForValue().set(Constants.REDIS_GOODS_COUNT + orderResponseVo.getGoodsId(), count - 1);
                    // redis中添加秒杀订单关系映射
                    redisTemplate.opsForValue().set(Constants.REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId(), orderResponseVo.getUserId());
                    // 发送消息
                    amqpTemplate.convertAndSend(Constants.EXCHANGE_ORDER_NAME,Constants.ROUTING_KEY_ORDER_NAME, orderResponseVo);
                    httpResult = new HttpResult(ResponseEnum.ORDER_SUCCESS, null);
                } else {
                    // 库存不足
                    httpResult = new HttpResult(ResponseEnum.SECKILL_FAIL, null);
                }
            } else {
                // 随机码错误
                httpResult = new HttpResult(ResponseEnum.SECKILL_RANDOMNUM_EXCEPTION, null);
            }

        } else {
            // 重复下单
            httpResult = new HttpResult(ResponseEnum.SECKILL_FAIL_USER_HAS_BUG, null);
        }
        return httpResult;
    }
}
