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
import xyz.wrywebsite.service.GoodsService;

import java.util.List;

/**
 * @author wry
 * @version 1.0
 * @classname GoodsInitialization
 * @description
 * @since 1.0
 */
@Component
@Slf4j
public class GoodsInitialization {

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 每3s同步商品列表到Redis
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void initListGoods() {
        List<Goods> list = goodsService.list();
        list.stream().forEach(goods -> {
            redisTemplate.opsForValue().set(Constants.REDIS_GOODS_LIST + goods.getGoodsId(), goods);
        });
    }


    /**
     * 库存消息,仅key不存在时设置
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void initGoodsCount() {
        List<Goods> list = goodsService.list();
        list.stream().forEach(goods -> {
            redisTemplate.opsForValue().setIfAbsent(Constants.REDIS_COUNT + goods.getGoodsId(), goods.getCount());
        });
    }
}
