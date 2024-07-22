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
 * @TableName t_goods
 */
@TableName(value ="t_goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String introduce;

    private BigDecimal price;

    private Integer count;

    private String randomNum;

    private String image;

    private Date startTime;

    private Date endTime;
}