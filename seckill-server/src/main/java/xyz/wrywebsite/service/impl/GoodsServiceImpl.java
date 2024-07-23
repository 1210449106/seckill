package xyz.wrywebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import xyz.wrywebsite.entity.Goods;
import xyz.wrywebsite.service.GoodsService;
import xyz.wrywebsite.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author wangr
* @description 针对表【t_goods】的数据库操作Service实现
* @createDate 2024-07-22 22:45:34
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

    @Resource
    private GoodsMapper goodsMapper;

}




