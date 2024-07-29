/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.OrderResponseVo;
import xyz.wrywebsite.seckillweb.service.SeckillService;

import java.util.Arrays;
import java.util.List;

import static xyz.wrywebsite.constant.Constants.*;

/**
 * @author wry
 * @version 1.0
 * @classname SeckillServiceImpl
 * @description
 * @since 1.0
 */
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {


    @Resource
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    /**
     * 读取lua脚本文件
     */
    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        // 设置脚本的返回类型
        redisScript.setResultType(Long.class);
        // 设置脚本源
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("seckill.lua")));
    }

    /**
     *
     * @param orderResponseVo 订单传输对象
     * @return -501用户重复下单,200用户秒杀成功,-502库存不足,-503被限流
     */
    @Override
    public Long doSeckill(OrderResponseVo orderResponseVo) {
        String mapKey = REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId();
        String countKey = REDIS_GOODS_COUNT + orderResponseVo.getGoodsId();
        // 使用当前秒数确认限流key，每秒一个
        String limitKey = REDIS_ORDER_SECKILL_LIMIT + System.currentTimeMillis()/1000;
        // 设置key列表
        List<String> keysList = Arrays.asList(mapKey, countKey, limitKey);
        Long rs = (Long) redisTemplate.execute(redisScript, keysList, orderResponseVo.getUserId().toString(), REDIS_SECKILL_MAX_VISITE.toString());
        log.debug("lua脚本返回值 [{}]", rs.toString());
        return rs;
    }

    @Override
    public void setSeckill(OrderResponseVo orderResponseVo) {
        redisTemplate.opsForValue().set(REDIS_ORDER_SECKILL  + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId(), orderResponseVo.getUserId().toString());
    }
}
