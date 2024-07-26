package xyz.wrywebsite;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
@MapperScan("xyz.wrywebsite.mapper")
@EnableScheduling
// 开启事务管理器
//@EnableTransactionManagement
@Slf4j
public class SeckillServerApplication {

    public static void main(String[] args) {
        // 使用非web方式启动springboot
        new SpringApplicationBuilder(SeckillServerApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
