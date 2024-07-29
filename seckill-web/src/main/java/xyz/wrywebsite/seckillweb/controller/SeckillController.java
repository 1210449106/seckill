/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.Goods;
import xyz.wrywebsite.constant.vo.HttpResult;
import xyz.wrywebsite.constant.vo.OrderResponseVo;
import xyz.wrywebsite.constant.vo.ResponseEnum;
import xyz.wrywebsite.seckillweb.service.GoodsService;
import xyz.wrywebsite.seckillweb.service.OrderMessageService;
import xyz.wrywebsite.seckillweb.service.SeckillService;

/**
 * @author wry
 * @version 1.0
 * @classname SeckillController
 * @description
 * @since 1.0
 */
//@RestController
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private SeckillService seckillService;

    @Resource
    private OrderMessageService orderMessageService;


    @PostMapping("/{randomNum}")
    public HttpResult saveOrder(@PathVariable("randomNum") String randomNum, @RequestBody OrderResponseVo orderResponseVo){
        log.debug("开始下单,randomNum [{}], userId [{}],goodsId [{}], goodsCount [{}] ", randomNum, orderResponseVo.getUserId(), orderResponseVo.getGoodsId(), orderResponseVo.getGoodsCount());
        // 获取商品对象
        Goods goods = goodsService.getGoodsById(orderResponseVo.getGoodsId());
        // 商品是否为空
        if (goods == null) {
            // 商品不存在
            log.debug("商品不存在");
            return new HttpResult(ResponseEnum.SECKILL_EXCEPTION, null);
        }
        // randomNum是否正确
        if (!goods.getRandomNum().equals(randomNum)) {
            // randomNum不正确
            log.debug("randomNum不正确");
            return new HttpResult(ResponseEnum.SECKILL_EXCEPTION, null);
        }
        // 秒杀是否开始
        if (goods.getStartTime().getTime() > System.currentTimeMillis() || goods.getEndTime().getTime() < System.currentTimeMillis()) {
            // 秒杀未开始或已结束
            log.debug("不在秒杀时段");
            return new HttpResult(ResponseEnum.SECKILL_EXCEPTION, null);
        }
        // 查询结果
        Long rs = seckillService.doSeckill(orderResponseVo);
        if (rs.equals(Constants.REDIS_SECKILL_CODE_USER_HAS_BUY)) {
            log.debug("用户重复下单");
            return new HttpResult(ResponseEnum.SECKILL_FAIL_USER_HAS_BUG, null);
        }
        if (rs.equals(Constants.REDIS_SECKILL_CODE_NOT_COUNT)) {
            log.debug("库存不足");
            return new HttpResult(ResponseEnum.SECKILL_FAIL, null);
        }
        // 判断成功,发送消息,添加映射关系
        if (rs.equals(Constants.REDIS_SECKILL_CODE_SUCCESS)) {
            Long rs2 = seckillService.doSeckill(orderResponseVo);
            if (rs2.equals(Constants.RABBIRMQ_RUSILT_SUCCESS)) {
                log.debug("秒杀成功");
                // 发送消息
//                orderMessageService.sendOrder(orderResponseVo);
                // 添加映射关系
                seckillService.setSeckill(orderResponseVo);
                return new HttpResult(ResponseEnum.SECKILL_SUCCESS, null);
            }
        }
        log.debug("未知原因失败");
        // 异常原因失败
        return new HttpResult(ResponseEnum.SECKILL_EXCEPTION, null);
    }
}
