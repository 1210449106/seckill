/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wrywebsite.constant.vo.*;
import xyz.wrywebsite.seckillweb.service.GoodsService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wry
 * @version 1.0
 * @classname GoodsController
 * @description 商品控制器
 * @since 1.0
 */
@RestController()
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 获取商品列表(无随机数)
     * @return
     */
    @GetMapping
    public HttpResult goodList() {
        // 获取goods列表
        List<Goods> goodsList = goodsService.listGoods();
        // 生成goodsList传输对象列表
        List<GoodsListResponseVo> goodsListResponseVoList = goodsList.stream()
                // 映射新列表
                .map(goods -> {
                    GoodsListResponseVo goodsListResponseVo = new GoodsListResponseVo();
                    BeanUtils.copyProperties(goods, goodsListResponseVo);
                    // 设置Status和gapTime
                    Long nowTime = System.currentTimeMillis();
                    if ( nowTime < goods.getStartTime().getTime() ) {
                        // 秒杀未开始,设置status
                        goodsListResponseVo.setStatus(0);
                        // 统计距离开始还有多久
                        goodsListResponseVo.setGapTime(goods.getStartTime().getTime() - nowTime);
                    } else if ( nowTime > goods.getEndTime().getTime() ) {
                        // 秒杀已结束，设置状态
                        goodsListResponseVo.setStatus(2);
                    } else {
                        goodsListResponseVo.setStatus(1);
                    }
                    // 返回复制后的数据
                    return goodsListResponseVo;
                })
                // 收集为List
                .collect(Collectors.toList());
        HttpResult httpResult = new HttpResult(ResponseEnum.GOODS_SUCCESS,goodsListResponseVoList);
        return httpResult;
    }

    @GetMapping("/{goodsId}")
    public HttpResult goodsDetail(@PathVariable("goodsId") Integer goodsId) {
        Goods goods = goodsService.getGoodsById(goodsId);
        if (goods == null) {
            return new HttpResult(ResponseEnum.GOODS_FAIL, null);
        }
        // 判断秒杀是否已开始
        GoodsDetailResponseVo goodsDetailResponseVo = new GoodsDetailResponseVo();
        goodsDetailResponseVo.setStatus(1);
        BeanUtils.copyProperties(goods, goodsDetailResponseVo);
        Long nowTime = System.currentTimeMillis();
        if ( nowTime < goods.getStartTime().getTime() ) {
            // 秒杀未开始,删除随机数
            goodsDetailResponseVo.setRandomNum(null);
            goodsDetailResponseVo.setStatus(0);
            // 统计距离开始还有多久
            goodsDetailResponseVo.setGapTime(goods.getStartTime().getTime() - nowTime);
        } else if ( nowTime > goods.getEndTime().getTime() ) {
            // 秒杀已结束，删除随机数
            goodsDetailResponseVo.setRandomNum(null);
            goodsDetailResponseVo.setStatus(2);
        } else {
            goodsDetailResponseVo.setStatus(1);
        }
        return new HttpResult(ResponseEnum.GOODS_SUCCESS, goodsDetailResponseVo);
    }
}
