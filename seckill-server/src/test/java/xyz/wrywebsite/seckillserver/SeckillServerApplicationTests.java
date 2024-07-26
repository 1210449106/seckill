package xyz.wrywebsite.seckillserver;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.wrywebsite.constant.Constants;
import xyz.wrywebsite.constant.vo.OrderResponseVo;

@SpringBootTest
@Slf4j
class SeckillServerApplicationTests {

    @Resource
    private AmqpTemplate amqpTemplate;


    @Test
    public void testSubmitOrder() {
        OrderResponseVo orderResponseVo = OrderResponseVo
                .builder()
                .userId(1)
                .goodsId(1)
                .goodsCount(1)
                .build();
        amqpTemplate.convertAndSend(Constants.EXCHANGE_ORDER_NAME, Constants.ROUTING_KEY_ORDER_NAME, orderResponseVo);
    }

}
