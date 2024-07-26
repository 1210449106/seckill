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
import xyz.wrywebsite.seckillweb.service.GoodsService;
import xyz.wrywebsite.constant.vo.Goods;

import java.util.List;

/**
 * @author wry
 * @version 1.0
 * @classname GoodsServiceImplTest
 * @description
 * @since 1.0
 */
@SpringBootTest
@Slf4j
public class GoodsServiceImplTest {
    @Resource
    private GoodsService goodsService;

    @Test
    public void testListGoods(){
        List<Goods> goodsList = goodsService.listGoods();
        log.info(goodsList.toString());
    }

    @Test
    public void testGetGoodsById(){
        Goods goods = goodsService.getGoodsById(1);
        log.info(goods.toString());
    }
}
