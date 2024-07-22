package xyz.wrywebsite.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer goodsId;

    private Integer userId;

    private BigDecimal goodsPrice;

    private String goodsCount;

    private BigDecimal totalPrice;

    private Integer status;

    private Date creatTime;

    private Date updateTime;
}