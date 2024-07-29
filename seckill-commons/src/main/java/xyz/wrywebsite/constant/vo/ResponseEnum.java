package xyz.wrywebsite.constant.vo;


import lombok.Getter;

public enum ResponseEnum {

    // 商品查询
    GOODS_SUCCESS(200, "获取商品信息成功"),
    GOODS_FAIL(500, "获取商品信息失败"),

    // 秒杀结果
    SECKILL_SUCCESS(200,"秒杀成功,请稍候查看订单"),
    // 秒杀失败
    SECKILL_FAIL(500,"来晚了，商品已经没有了~"),
    // 用户重复下单
    SECKILL_FAIL_USER_HAS_BUG(501,"您已经成功秒杀到了,不能贪心哦"),
    // 参数校验错误
    SECKILL_EXCEPTION(502,"网络异常"),
    // 被限流
    SECKILL_LIMIT_VISIT(503,"当前下单人数太多了，请重试"),

    // 订单查询
    ORDER_SUCCESS(200, "订单查询成功"),
    ORDER_FAIL(500,"订单查询失败"),

    // 支付结果查询
    ORDER_PAY_WAIT(200, "订单等待支付"),
    ORDER_PAY_SUCCESS(201, "订单支付成功"),
    ORDER_PAY_FAIL(501, "订单超时未支付,已关闭")
    ;

    // 属性
    @Getter
    private Integer code;
    @Getter
    private String message;

    // 构造方法
    ResponseEnum() {
    }

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
