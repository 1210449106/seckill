/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.service;

import xyz.wrywebsite.constant.vo.Goods;

import java.util.List;

/**
 * @author wry
 * @version 1.0
 * @classname GoodsService
 * @description
 * @since 1.0
 */
public interface GoodsService {
    /**
     * 获取商品列表
     * @return
     */
    List<Goods> listGoods();

    /**
     * 根据获取商品信息
     * @param id 商品id
     * @return
     */
    Goods getGoodsById(Integer id);
}
