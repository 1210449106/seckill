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

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wry
 * @version 1.0
 * @classname goodsDetailResponseVo
 * @description
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class GoodsDetailResponseVo {

    private Integer goodsId;

    private String title;

    private String introduce;

    private BigDecimal price;

    private Integer count;

    private String randomNum;

    private String image;

    private Date startTime;

    private Date endTime;

    // 状态 0表示未开始,1表示开始,2表示已结束
    private Integer status;

    // 差距时间
    private Long gapTime;
}
