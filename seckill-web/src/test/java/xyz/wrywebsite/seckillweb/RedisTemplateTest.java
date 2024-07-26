/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import xyz.wrywebsite.constant.Constants;

/**
 * @author wry
 * @version 1.0
 * @classname RedisTemplateTest
 * @description
 * @since 1.0
 */
@SpringBootTest
@Slf4j
public class RedisTemplateTest {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testHasKey(){
        System.out.println("商品1是否存在" + redisTemplate.hasKey(Constants.REDIS_GOODS_LIST + "1"));
        System.out.println("商品100是否存在" + redisTemplate.hasKey(Constants.REDIS_GOODS_LIST + "100"));
    }
}
