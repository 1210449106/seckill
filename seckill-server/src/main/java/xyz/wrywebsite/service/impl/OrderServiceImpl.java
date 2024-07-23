package xyz.wrywebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import xyz.wrywebsite.entity.Order;
import xyz.wrywebsite.service.OrderService;
import xyz.wrywebsite.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author wangr
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2024-07-22 22:45:15
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    private OrderMapper orderMapper;
}




