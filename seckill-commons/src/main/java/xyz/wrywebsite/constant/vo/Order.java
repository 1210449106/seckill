package xyz.wrywebsite.constant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Order implements Serializable {

    private Integer orderId;

    private Integer goodsId;

    private Integer userId;

    private BigDecimal goodsPrice;

    private Integer goodsCount;

    private BigDecimal totalPrice;

    private Integer status;

    private Date creatTime;

    private Date updateTime;
}