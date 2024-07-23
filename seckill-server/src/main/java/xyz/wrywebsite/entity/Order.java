package xyz.wrywebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
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