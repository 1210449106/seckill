package xyz.wrywebsite.constant.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName t_goods
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Goods implements Serializable {

    private Integer goodsId;

    private String title;

    private String introduce;

    private BigDecimal price;

    private Integer count;

    private String randomNum;

    private String image;

    private Date startTime;

    private Date endTime;
}