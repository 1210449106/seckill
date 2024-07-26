/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.constant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wry
 * @version 1.0
 * @classname OrderResponseVo
 * @description 订单提交对象
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class OrderResponseVo {
    // 商品id
    private Integer goodsId;

    // 用户id
    private Integer userId;

    // 商品数量
    private Integer goodsCount;
}
