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
import xyz.wrywebsite.seckillweb.service.GoodsService;
import xyz.wrywebsite.constant.vo.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wry
 * @version 1.0
 * @classname GoodsServiceImpl
 * @description
 * @since 1.0
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<Goods> listGoods() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Goods> goodsList = new ArrayList<>();
        Set keys = redisTemplate.keys(Constants.REDIS_GOODS_LIST + "*");
        keys.forEach(key -> {
            String goodsListStr = (String) redisTemplate.opsForValue().get(key);
            try {
                goodsList.add(objectMapper.readValue(goodsListStr, Goods.class));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        return goodsList;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();
        String goodsStr = (String) redisTemplate.opsForValue().get(Constants.REDIS_GOODS_LIST + id);
        Goods goods = null;
        try {
            goods = objectMapper.readValue(goodsStr, Goods.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return goods;
    }
}
