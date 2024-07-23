package xyz.wrywebsite.seckillserver;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.OrderMessageVo;
import xyz.wrywebsite.entity.Goods;
import xyz.wrywebsite.entity.Order;
import xyz.wrywebsite.service.GoodsService;
import xyz.wrywebsite.service.OrderService;

import java.util.List;

@SpringBootTest
@Slf4j
class SeckillServerApplicationTests {

    @Resource
    private AmqpTemplate amqpTemplate;


    @Test
    public void testSubmitOrder() {
        OrderMessageVo orderMessageVo = OrderMessageVo
                .builder()
                .userId(1)
                .goodsId(1)
                .goodsCount(1)
                .build();
        amqpTemplate.convertAndSend(Constants.EXCHANGE_ORDER_NAME, Constants.ROUTING_KEY_ORDER_NAME, orderMessageVo);
    }

}
