package xyz.wrywebsite.seckillweb;/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wrywebsite.constant.vo.OrderResponseVo;
import xyz.wrywebsite.seckillweb.service.SeckillService;

/**
 * @author wry
 * @version 1.0
 * @classname xyz.wrywebsite.seckillweb.SeckillServiceTest
 * @description
 * @since 1.0
 */
@SpringBootTest
public class SeckillServiceTest {

    @Resource
    private SeckillService seckillService;

    @Test
    public void testDoSeckill() {
        Long l = seckillService.doSeckill(new OrderResponseVo(2,100, 1));
        Assertions.assertEquals(200, l);
    }

}
