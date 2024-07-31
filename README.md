# 项目架构

![image.png](https://cdn.nlark.com/yuque/0/2024/png/21895417/1721621798851-a2a1792b-d71d-4e7e-a0b5-57bb75f87a0b.png#averageHue=%23fdfcfc&clientId=u8cfa02ca-f449-4&from=paste&height=849&id=uba429c9a&originHeight=849&originWidth=1247&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110577&status=done&style=none&taskId=u1b9fefa6-16ba-4ace-b202-f940b095c56&title=&width=1247)

# 技术选型

- db
  - MySQL
    - 数据库连接池:Druid
- 后端
  - SpringBoot
  - Mybatis-Plus
- 中间件
  - Redis
  - RabbitMQ
- 前端
  - vue
- 测试
  - JMeter

# 数据库设计

## 商品表

t_goods

| 字段名           | 数据类型     | 备注             |
| ---------------- | ------------ | ---------------- |
| goods_id         | int          | 商品id，自增主键 |
| goods_title      | varchat(255) | 商品标题         |
| goods_introduce  | varchar(255) | 商品详情         |
| goods_price      | decimal      | 商品价格         |
| goods_count      | int          | 库存数量         |
| goods_random_num | varchat(255) | 随机码           |
| goods_image      | varchat(255) | 图片地址         |
| goods_start_time | datetime     | 秒杀开始时间     |
| goods_end_time   | datetime     | 秒杀结束时间     |

## 订单表

t_order

| 字段名            | 数据类型 | 备注                             |
| ----------------- | -------- | -------------------------------- |
| order_id          | int      | 订单id，自增主键                 |
| order_goods_id    | int      | 商品id                           |
| order_user_id     | int      | 下单用户id                       |
| order_goods_price | decimal  | 商品单价                         |
| order_goods_count | int      | 商品数量                         |
| order_total_price | decimal  | 订单总价                         |
| order_status      | int      | 订单状态,0已提交,1已支付,2已关闭 |
| order_creat_time  | time     | 订单提交时间                     |
| order_update_time | time     | 订单支付时间                     |

## 商品表数据插入

```sql
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (1, 'iPhone X', '苹果全面屏手机 深空灰色 移动联通电信 256GB', '49020387-0a86-4f3f-9e13-463688ad4ef1', 8388.00, 'http://localhost:8080/image/01.jpg', 1000, '2022-09-01 09:30:01', '2022-09-30 23:59:59');
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (2, '小米6', '全网通 4GB+64GB 亮黑色 移动联通电信4G手机 双卡双待', '0c7dab04-0743-4170-b2cf-144e8653b58e', 2099.00, 'http://localhost:8080/image/02.jpg', 1000, '2024-06-01 09:30:01', '2024-06-06 23:59:59');
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (3, 'OPPO R11s', '双卡双待全面屏拍照手机 香槟色 全网通(4G RAM+64G ROM)官方标配', '8fcdccf4-1cf9-480b-b0aa-ce53fb4e206b', 2999.00, 'http://localhost:8080/image/03.jpg', 0, '2023-12-10 09:30:01', '2024-06-30 23:59:59');
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (4, '华为 nova2S', '全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待', '3908fbbc-c15f-4d4e-ad68-b8e9e3643342', 3099.00, 'http://localhost:8080/image/04.jpg', 1000, '2022-09-01 09:30:01', '2022-11-30 23:59:59');
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (5, 'vivo X20Plus', '全面屏双摄拍照手机 4GB+64GB 玫瑰金 玫瑰金 4GB+ 64GB', 'ed859065-7b0b-4a85-8827-36c588c1f899', 2099.00, 'http://localhost:8080/image/05.jpg', 1000, '2022-09-01 09:30:01', '2022-09-30 23:59:59');
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (6, '华硕（ASUS）', 'F556UQ独显GT940游戏本15.6英寸顽石战斗版手提商务笔记本电脑', 'd5dfb679-f433-4ae1-9b2e-e2332304660d', 5099.00, 'http://localhost:8080/image/06.jpg', 1000, '2024-05-10 09:30:01', '2024-05-30 23:59:59');
INSERT INTO `t_goods`(goods_id,goods_title,goods_introduce,goods_random_num,goods_price,goods_image,goods_count,goods_start_time,goods_end_time) VALUES (7, 'ThinkPad S1', '翻转轻薄碳纤维手写本（i5-8250U 8G 256GSSD 背光键盘 FHD）', 'ab17ba3d-01ca-4114-9c64-836a34014ef0', 9499.00, 'http://localhost:8080/image/07.jpg', 1000, '2022-12-01 16:30:01', '2022-12-08 23:59:59');
```

## 数据库SQL脚本

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : seckill
 Source Server Type    : MySQL
 Source Server Version : 80401
 Source Host           : 192.168.68.200:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 80401
 File Encoding         : 65001

 Date: 23/07/2024 02:38:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id，自增主键',
  `goods_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品详情',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `goods_count` int NULL DEFAULT NULL COMMENT '库存数量',
  `goods_random_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '随机码',
  `goods_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `goods_start_time` datetime NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `goods_end_time` datetime NULL DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, 'iPhone X', '苹果全面屏手机 深空灰色 移动联通电信 256GB', 8388.00, 1000, '49020387-0a86-4f3f-9e13-463688ad4ef1', 'http://localhost:8080/image/01.jpg', '2022-09-01 09:30:01', '2022-09-30 23:59:59');
INSERT INTO `t_goods` VALUES (2, '小米6', '全网通 4GB+64GB 亮黑色 移动联通电信4G手机 双卡双待', 2099.00, 1000, '0c7dab04-0743-4170-b2cf-144e8653b58e', 'http://localhost:8080/image/02.jpg', '2024-06-01 09:30:01', '2024-06-06 23:59:59');
INSERT INTO `t_goods` VALUES (3, 'OPPO R11s', '双卡双待全面屏拍照手机 香槟色 全网通(4G RAM+64G ROM)官方标配', 2999.00, 0, '8fcdccf4-1cf9-480b-b0aa-ce53fb4e206b', 'http://localhost:8080/image/03.jpg', '2023-12-10 09:30:01', '2024-06-30 23:59:59');
INSERT INTO `t_goods` VALUES (4, '华为 nova2S', '全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待', 3099.00, 1000, '3908fbbc-c15f-4d4e-ad68-b8e9e3643342', 'http://localhost:8080/image/04.jpg', '2022-09-01 09:30:01', '2022-11-30 23:59:59');
INSERT INTO `t_goods` VALUES (5, 'vivo X20Plus', '全面屏双摄拍照手机 4GB+64GB 玫瑰金 玫瑰金 4GB+ 64GB', 2099.00, 1000, 'ed859065-7b0b-4a85-8827-36c588c1f899', 'http://localhost:8080/image/05.jpg', '2022-09-01 09:30:01', '2022-09-30 23:59:59');
INSERT INTO `t_goods` VALUES (6, '华硕（ASUS）', 'F556UQ独显GT940游戏本15.6英寸顽石战斗版手提商务笔记本电脑', 5099.00, 1000, 'd5dfb679-f433-4ae1-9b2e-e2332304660d', 'http://localhost:8080/image/06.jpg', '2024-05-10 09:30:01', '2024-05-30 23:59:59');
INSERT INTO `t_goods` VALUES (7, 'ThinkPad S1', '翻转轻薄碳纤维手写本（i5-8250U 8G 256GSSD 背光键盘 FHD）', 9499.00, 1000, 'ab17ba3d-01ca-4114-9c64-836a34014ef0', 'http://localhost:8080/image/07.jpg', '2022-12-01 16:30:01', '2022-12-08 23:59:59');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_goods_id` int NULL DEFAULT NULL,
  `order_user_id` int NULL DEFAULT NULL,
  `order_goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `order_goods_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_total_price` decimal(10, 2) NULL DEFAULT NULL,
  `order_status` int NULL DEFAULT NULL,
  `order_creat_time` datetime NULL DEFAULT NULL,
  `order_update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

```

# Redis设计

seckill

## 储存商品信息

| 键                    | 示例    | 值             | 示例 |
| --------------------- | ------- | -------------- | ---- |
| goods:list:{goods_id} | goods:1 | JSON(商品信息) |      |

 |

## 储存库存信息

| 键                     | 示例    | 值                  | 示例 |
| ---------------------- | ------- | ------------------- | ---- |
| goods:count:{goods_id} | goods:1 | String 剩余库存数量 | 1000 |

## 储存秒杀对应关系以及支付结果

| 键                                  | 示例               | 值       | 示例 |
| ----------------------------------- | ------------------ | -------- | ---- |
| orders:seckill:{goods_id}:{user_id} | orders:seckill:1:1 | 支付结果 | 1    |

## 储存订单信息

| 键                     | 示例           | 值       | 示例 |
| ---------------------- | -------------- | -------- | ---- |
| orders:order:{orderId} | orders:order:1 | 订单信息 |      |

 |

## 储存订单支付结果

| 键                   | 示例         | 值                                           | 示例 |
| -------------------- | ------------ | -------------------------------------------- | ---- |
| orders:pay:{orderid} | odrder:pay:1 | String 0表示待支付,1表示已支付,2表示支付超时 | 0    |

# RabbitMQ设计

- 所在虚拟主机:seckill

## 下单

| 交换机名               | 交换机类型     | 队列名              | 路由键                   |
| ---------------------- | -------------- | ------------------- | ------------------------ |
| exchange.seckill.order | DirectExchange | queue.seckill.order | routingkey.seckill.order |

## 确认支付状态

| 交换机名             | 交换机类型     | 队列名            | 路由键                 |
| -------------------- | -------------- | ----------------- | ---------------------- |
| exchange.seckill.pay | CustomExchange | queue.seckill.pay | routingkey.seckill.pay |

# Http Api设计

[https://apifox.com/apidoc/shared-4d1b09b7-6ae0-4719-9600-babfca8fa977](https://apifox.com/apidoc/shared-4d1b09b7-6ae0-4719-9600-babfca8fa977)

# seckill-commons

## 常量池xyz.wrywebsite.constant

```java
public interface Constants {

    // Redis
    // 商品前缀key
    String REDIS_GOODS_PREF = "goods";
    // 商品列表信息key
    String REDIS_GOODS_LIST = REDIS_GOODS_PREF + ":" + "list" + ":";
    // 商品库存信息key
    String REDIS_COUNT = REDIS_GOODS_PREF + ":" + "count" + ":";

    // 订单前缀key
    String REDIS_ORDERS_PREF = "orders";
    // 订单key
    String REDIS_ORDER = REDIS_ORDERS_PREF + ":" + "order" + ":";
    // 订单支付结果key
    String REDIS_PAY = REDIS_ORDERS_PREF + ":" + "pay" + ":";


    // RabbieMQ
    // exchange name
    // 前缀
    String EXCHANGE_NAME_PREF = "exchange.seckill";
    // 负责接收下单消息的交换机
    String EXCHANGE_ORDER_NAME = EXCHANGE_NAME_PREF + "." + "order";
    // 支付状态确认交换机(延迟交换机)
    String EXCHANGE_PAY_NAME = EXCHANGE_NAME_PREF + "." + "pay";

    // queueName
    // 前缀
    String QUEUE_NAME_PREF = "queue.seckill";
    // 负责接收下单消息的queue
    String QUEUE_ORDER_NAME = QUEUE_NAME_PREF + "." + "order";
    // 负责确认支付状态的queueue
    String QUEUE_PAY_NAME = QUEUE_NAME_PREF + "." + "pay";

    //routingkey
    // 前缀
    String ROUTING_KEY_PREF = "routingkey.seckill";
    // 负责接收下单消息队列的routingkey
    String ROUTING_KEY_ORDER_NAME = ROUTING_KEY_PREF + "." + "order";
    // 负责确认支付状态队列的routingkey
    String ROUTING_PAY_NAME = ROUTING_KEY_PREF + "." + "pay";
}
```

### 数据传输对象xyz.wrywebsite.constant.vo

#### 响应结果枚举

```java
public enum ResponseEnum {

    // 商品查询
    GOODS_SUCCESS(200, "获取商品信息成功"),
    GOODS_FAIL(500, "获取商品信息失败"),

    // 秒杀结果
    SECKILL_RESULT(200,"秒杀成功,请稍候查看订单"),
    // 秒杀失败
    SECKILL_FAIL(500,"来晚了，商品已经没有了~"),
    // 用户重复下单
    USER_IS_BUG(501,"您已经成功秒杀到了,不能贪心哦"),

    // 订单查询
    ORDER_SUCCESS(200, "订单查询成功"),
    ORDER_FAIL(500,"订单查询失败")
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

```

#### http请求响应类型

```java
@Data
public class HttpResult {
    private Integer code;
    private String msg;
    private Object data;

    public HttpResult(ResponseEnum responseEnum, Object data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMessage();
        this.data = data;
    }
}
```

# seckill-server

## entity、srvice、dao使用MybaitsX生成

## Resid使用JSON存储value

```java
@Configuration
public class RedisTemplateConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
```

## SpringBoot使用非Web模式启动，并设置定时任务

```java
@SpringBootApplication
@MapperScan("xyz.wrywebsite.mapper")
@EnableScheduling
@Slf4j
public class SeckillServerApplication {

    @Resource
    private GoodsInitialization goodsInitialization;

    public static void main(String[] args) {
        // 使用非web方式启动springboot
        new SpringApplicationBuilder(SeckillServerApplication.class).web(WebApplicationType.NONE).run(args);
    }
}

```

## 定时任务类

- 读取goods列表，goods库存到Redis

```java
@Component
@Slf4j
public class GoodsInitialization {

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 每3s同步商品列表到Redis
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void initListGoods() {
        List<Goods> list = goodsService.list();
        list.stream().forEach(goods -> {
            redisTemplate.opsForValue().set(Constants.REDIS_GOODS_LIST + goods.getId(), goods);
        });
    }


    /**
     * 每3s同步库存信息到Redis
     */
    @Scheduled(cron = "0/3 * * * * *")
    public void initGoodsCount() {
        List<Goods> list = goodsService.list();
        list.stream().forEach(goods -> {
            redisTemplate.opsForValue().set(Constants.REDIS_COUNT + goods.getId(), goods.getCount());
        });
    }
}
```

# seckill-web

## Controller

### GoodsController

```java
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
```

### OrderController

```java
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;



    /**
     * 根据id获取订单
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public HttpResult getOrderById(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        // 返回数据
        return new HttpResult(ResponseEnum.ORDER_SUCCESS, order);
    }

    @GetMapping("/result/{goodsId}/{userId}")
    public HttpResult getOrderResult(@PathVariable("goodsId") Integer goodsId, @PathVariable("userId") Integer userId) {
        Integer orderResult = orderService.getOrderResult(goodsId, userId);
        HttpResult httpResult = null;
        if (orderResult.equals(0)) {
            // 待支付
            httpResult = new HttpResult(ResponseEnum.ORDER_PAY_WAIT, null);
        }
        if (orderResult.equals(1)) {
            // 已经支付
            httpResult = new HttpResult(ResponseEnum.ORDER_PAY_SUCCESS, null);
        }
        if (orderResult.equals(2)) {
            // 支付超时
            httpResult = new HttpResult(ResponseEnum.ORDER_PAY_FAIL, null);
        }
        return httpResult;
    }
}

```

### SeckillController

```java
@RestController
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
        // 执行秒杀
        Long rs = seckillService.doSeckill(orderResponseVo);
        log.debug("秒杀结果为[{}]",rs.toString());
        if (rs.equals(Constants.REDIS_SECKILL_CODE_SUCCESS)) {
            // 秒杀成功
            // 发送消息
            orderMessageService.sendOrder(orderResponseVo);
            return new HttpResult(ResponseEnum.SECKILL_SUCCESS, null);
        }
        if (rs.equals(Constants.REDIS_SECKILL_CODE_USER_HAS_BUY)) {
            // 重复下单
            return new HttpResult(ResponseEnum.SECKILL_FAIL_USER_HAS_BUG, null);
        }
        if (rs.equals(Constants.REDIS_SECKILL_CODE_NOT_COUNT)) {
            // 库存不足
            return new HttpResult(ResponseEnum.SECKILL_FAIL, null);
        }
        if (rs.equals(Constants.REDIS_SECKILL_LIMIT)) {
            // 被限流
            return new HttpResult(ResponseEnum.SECKILL_LIMIT_VISIT, null);
        }
        log.debug("未知原因失败");
        // 异常原因失败
        return new HttpResult(ResponseEnum.SECKILL_EXCEPTION, null);
    }
}
```

## Service

### GoodsServeice

```java
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<Goods> listGoods() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Goods> goodsList = new ArrayList<>();
        Set keys = redisTemplate.keys(Constants.REDIS_GOODS_LIST + "*");
        keys.forEach(key -> {
            String goodsListStr = (String) redisTemplate.opsForValue().get(key);
            try {
                goodsList.add(objectMapper.readValue(goodsListStr, Goods.class));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        return goodsList;
    }

    @Override
    public Goods getGoodsById(Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();
        String goodsStr = (String) redisTemplate.opsForValue().get(Constants.REDIS_GOODS_LIST + id);
        Goods goods = null;
        try {
            goods = objectMapper.readValue(goodsStr, Goods.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return goods;
    }

    @Override
    public Integer getGoodsCount(Integer goodsId) {
        Integer count = Integer.valueOf((String) redisTemplate.opsForValue().get(Constants.REDIS_GOODS_COUNT + goodsId));
        return count;
    }

    @Override
    public void deductionGoodsCount(Integer goodsId) {
        redisTemplate.opsForValue().decrement(Constants.REDIS_GOODS_COUNT + goodsId);
    }
}
```

### OrderService

```java
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private GoodsService goodsService;

    @Override
    public Order getOrderById(Integer orderId) {
        // 根据id获取对象
        String orderStr = (String) redisTemplate.opsForValue().get(Constants.REDIS_ORDER + orderId);
        log.debug("orderStr={}", orderStr);
        // 反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try {
            // 获取订单对象
            order = objectMapper.readValue(orderStr, Order.class);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return order;
    }

    /**
     * 获取订单支付结果
     * @param goodsId 商品Id
     * @param userId 用户Id
     * @return 0 待支付，1 已支付,2 支付超时已作废
     */
    @Override
    public Integer getOrderResult(Integer goodsId, Integer userId) {
        Integer rs = Integer.parseInt((String) redisTemplate.opsForValue().get(REDIS_ORDER_SECKILL + goodsId + ":" + userId));
        return rs;
    }
}
```

### OrderMessageService

- 用于发送下单消息

```java
@Service
@Slf4j
public class OrderMessageServiceImpl implements OrderMessageService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public Long sendOrder(OrderResponseVo orderResponseVo) {
        // 发送下单消息
        amqpTemplate.convertAndSend(Constants.EXCHANGE_ORDER_NAME, Constants.ROUTING_KEY_ORDER_NAME, orderResponseVo);
        log.debug("下单消息已发送，订单 [{}]", orderResponseVo.toString());
        return Constants.RABBIRMQ_RUSILT_SUCCESS;
    }
}
```

### SeckillServie

```java
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {


    @Resource
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    /**
     * 读取lua脚本文件
     */
    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        // 设置脚本的返回类型
        redisScript.setResultType(Long.class);
        // 设置脚本源
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("seckill.lua")));
    }

    /**
     *
     * @param orderResponseVo 订单传输对象
     * @return -501用户重复下单,200用户秒杀成功,-502库存不足,-503被限流
     */
    @Override
    public Long doSeckill(OrderResponseVo orderResponseVo) {
        String mapKey = REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId();
        String countKey = REDIS_GOODS_COUNT + orderResponseVo.getGoodsId();
        // 使用当前秒数确认限流key，每秒一个
        String limitKey = REDIS_ORDER_SECKILL_LIMIT + System.currentTimeMillis()/1000;
        // 设置key列表
        List<String> keysList = Arrays.asList(mapKey, countKey, limitKey);
        Long rs = (Long) redisTemplate.execute(redisScript, keysList, orderResponseVo.getUserId().toString(), REDIS_SECKILL_MAX_VISITE.toString());
        log.debug("lua脚本返回值 [{}]", rs.toString());
        return rs;
    }

    @Override
    public void setSeckill(OrderResponseVo orderResponseVo) {
        redisTemplate.opsForValue().set(REDIS_ORDER_SECKILL  + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId(), orderResponseVo.getUserId().toString());
    }
}
```

# 分布式锁

## 使用jmeter进行压力测试

发现存在超售问题

- 原因:
  - doSeckill()方法被多个线程同时执行导致的

## 解决方案

### 线程锁(单体)

```java
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {


    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private GoodsService goodsService;


    /**
     *
     * @param orderResponseVo 订单传输对象
     * @return -501用户重复下单,200用户秒杀成功,-502库存不足
     */
    @Override
    public synchronized Long doSeckill(OrderResponseVo orderResponseVo) {
        // 用户是否重复下单
        Boolean rs = redisTemplate.hasKey(Constants.REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId());
        if (rs) {
            // 用户重复下单
            return REDIS_SECKILL_CODE_USER_HAS_BUY;
        }
        // 首次下单,获取商品库存
        Integer goodsCount = goodsService.getGoodsCount(orderResponseVo.getGoodsId());
        log.debug("goodsCount=[{}]", goodsCount);
        if (goodsCount < orderResponseVo.getGoodsCount() || goodsCount <= 0) {
            //库存不充足
            return REDIS_SECKILL_CODE_NOT_COUNT;
        } else {
            // 库存充足，扣减库存
            goodsService.deductionGoodsCount(orderResponseVo.getGoodsId());
            log.debug("扣减库存");
        }
        return REDIS_SECKILL_CODE_SUCCESS;
    }
}
```

### lua脚本

确保查库存，减库存，设置映射Key的原子性

1. resource下新建lua脚本

```lua
-- 映射关系key
local mapperKey = KEYS[1]
-- 库存key
local countKey = KEYS[2]
-- 用户id
local userId = ARGV[1]

-- 判断用户是否买过
local isBuy = tonumber(redis.call("exists", mapperKey))
if(isBuy == 1) then
    return -501
end

-- 判断库存是否充足
-- 获取库存
local count = tonumber(redis.call("get", countKey))
if (count <= 0) then
    -- 库存不足
    return -502
end

-- 库存充足且首次下单
-- 写入映射,扣减库存
redis.call("set", mapperKey, userId)
redis.call("decr", countKey)
return 200
```

2. SeckillService中调用

```java
@Service
@Slf4j
public class SeckillServiceImpl implements SeckillService {


    @Resource
    private RedisTemplate redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    /**
     * 读取lua脚本文件
     */
    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        // 设置脚本的返回类型
        redisScript.setResultType(Long.class);
        // 设置脚本源
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("seckill.lua")));
    }

    /**
     *
     * @param orderResponseVo 订单传输对象
     * @return -501用户重复下单,200用户秒杀成功,-502库存不足
     */
    @Override
    public Long doSeckill(OrderResponseVo orderResponseVo) {
        String mapKey = REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId();
        String countKey = REDIS_GOODS_COUNT + orderResponseVo.getGoodsId();
        // 设置key列表
        List<String> keysList = Arrays.asList(mapKey, countKey);
        Long rs = (Long) redisTemplate.execute(redisScript, keysList, orderResponseVo.getUserId().toString());
        log.debug("lua脚本返回值 [{}]", rs.toString());
        return rs;
    }

。。。。
}
```

# 限流

## 限流逻辑

下单前查询当前秒中的访问量，当访问量大于设定值的时候不处理,直接返回失败
key使用当前时间戳/1000（每秒一个key），lua脚本中使用incr设置/更新key,使用expire设置key有效存活时间,回收无用key

### lua脚本

```lua
-- 映射关系key
local mapperKey = KEYS[1]
-- 库存key
local countKey = KEYS[2]
-- 限流key
local limitKey = KEYS[3]
-- 用户id
local userId = ARGV[1]
-- 限流最大人数
local maxVisite = ARGV[2]

-- 获取当前访问人数
local visiteCount = tonumber(redis.call("get",limitKey) or "0")
-- 判断访问人数是否超标
if (visiteCount >= tonumber(maxVisite)) then
    -- 超标 更新数据后返回,拒绝处理
    return -503
end

-- 未超标,新建限流key或值+1
if (tonumber(redis.call("incr",limitKey)) == 1) then
    -- 设置有效时间
    redis.call("expire",limitKey,"2")
end

-- 判断用户是否买过
local isBuy = tonumber(redis.call("exists", mapperKey))
if(isBuy == 1) then
    return -501
end

-- 判断库存是否充足
-- 获取库存
local count = tonumber(redis.call("get", countKey))
if (count <= 0) then
    -- 库存不足
    return -502
end

-- 库存充足且首次下单
-- 写入映射,扣减库存
redis.call("set", mapperKey, userId)
redis.call("decr", countKey)
return 200
```

### SeckillService中调用

```java
    /**
     *
     * @param orderResponseVo 订单传输对象
     * @return -501用户重复下单,200用户秒杀成功,-502库存不足,-503被限流
     */
    @Override
    public Long doSeckill(OrderResponseVo orderResponseVo) {
        String mapKey = REDIS_ORDER_SECKILL + orderResponseVo.getGoodsId() + ":" + orderResponseVo.getUserId();
        String countKey = REDIS_GOODS_COUNT + orderResponseVo.getGoodsId();
        // 使用当前秒数确认限流key，每秒一个
        String limitKey = REDIS_ORDER_SECKILL_LIMIT + System.currentTimeMillis()/1000;
        // 设置key列表
        List<String> keysList = Arrays.asList(mapKey, countKey, limitKey);
        Long rs = (Long) redisTemplate.execute(redisScript, keysList, orderResponseVo.getUserId().toString(), REDIS_SECKILL_MAX_VISITE.toString());
        log.debug("lua脚本返回值 [{}]", rs.toString());
        return rs;
    }
```

# 项目地址

https://github.com/1210449106/seckill
