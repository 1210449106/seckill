/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.service;

import xyz.wrywebsite.constant.vo.Order;
import xyz.wrywebsite.constant.vo.OrderResponseVo;

/**
 * @author wry
 * @version 1.0
 * @classname OrderMessageService
 * @description RabbirMQ发送消息
 * @since 1.0
 */
public interface OrderMessageService {
    Long sendOrder(OrderResponseVo orderResponseVo);
}
