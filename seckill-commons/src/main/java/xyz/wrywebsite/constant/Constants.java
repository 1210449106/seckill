/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.constant;

/**
 * @author wry
 * @version 1.0
 * @classname Constants
 * @description
 * @since 1.0
 */
public interface Constants {

    // Redis
    // 商品前缀key
    String REDIS_GOODS_PREF = "goods";
    // 商品列表信息key
    String REDIS_GOODS_LIST = REDIS_GOODS_PREF + ":" + "list" + ":";
    // 商品库存信息key
    String REDIS_GOODS_COUNT = REDIS_GOODS_PREF + ":" + "count" + ":";

    // 订单前缀key
    String REDIS_ORDERS_PREF = "orders";
    // 订单key
    String REDIS_ORDER = REDIS_ORDERS_PREF + ":" + "order" + ":";
    // 订单秒杀关系key
    String REDIS_ORDER_SECKILL = REDIS_ORDERS_PREF + ":" + "seckill" + ":";
    // 订单秒杀限流key
    String REDIS_ORDER_SECKILL_LIMIT = REDIS_ORDERS_PREF + ":" + "limit:";

    // 订单秒杀结果
    // 成功
    Long REDIS_SECKILL_CODE_SUCCESS = 200L;
    // 重复下单
    Long REDIS_SECKILL_CODE_USER_HAS_BUY = -501L;
    // 库存不足
    Long REDIS_SECKILL_CODE_NOT_COUNT = -502L;
    // 被限流
    Long REDIS_SECKILL_LIMIT = -503L;
    // 最大访问量
    Long REDIS_SECKILL_MAX_VISITE = 0L;
    // 订单支付结果key
    String REDIS_ORDER_PAY = REDIS_ORDERS_PREF + ":" + "pay" + ":";


    // RabbieMQ
    // exchange name
    // 前缀
    String EXCHANGE_NAME_PREF = "exchange.seckill";
    // 负责接收下单消息的交换机
    String EXCHANGE_ORDER_NAME = EXCHANGE_NAME_PREF + "." + "order";
    // 支付状态确认交换机(延迟交换机)
    String EXCHANGE_PAY_NAME = EXCHANGE_NAME_PREF + "." + "pay";

    // queueName
    // 前缀
    String QUEUE_NAME_PREF = "queue.seckill";
    // 负责接收下单消息的queue
    String QUEUE_ORDER_NAME = QUEUE_NAME_PREF + "." + "order";
    // 负责确认支付状态的queueue
    String QUEUE_PAY_NAME = QUEUE_NAME_PREF + "." + "pay";
    // 确认支付状态延时时间
    Integer QUEUE_PAY_DELAY_TIME = 40*1000;

    //routingkey
    // 前缀
    String ROUTING_KEY_PREF = "routingkey.seckill";
    // 负责接收下单消息队列的routingkey
    String ROUTING_KEY_ORDER_NAME = ROUTING_KEY_PREF + "." + "order";
    // 负责确认支付状态队列的routingkey
    String ROUTING_KEY_PAY_NAME = ROUTING_KEY_PREF + "." + "pay";

    // 结果
    Long RABBIRMQ_RUSILT_SUCCESS = 200L;
}
