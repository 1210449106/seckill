package xyz.wrywebsite;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import xyz.wrywebsite.task.GoodsInitialization;

import java.util.Date;

@SpringBootApplication
@MapperScan("xyz.wrywebsite.mapper")
@EnableScheduling
// 开启事务管理器
//@EnableTransactionManagement
@Slf4j
public class SeckillServerApplication {

    @Resource
    private GoodsInitialization goodsInitialization;

    public static void main(String[] args) {
        // 使用非web方式启动springboot
        new SpringApplicationBuilder(SeckillServerApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
