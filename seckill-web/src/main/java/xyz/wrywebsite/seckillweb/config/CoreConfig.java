/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wry
 * @version 1.0
 * @classname awd
 * @description
 * @since 1.0
 */
@Configuration
public class CoreConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //跨域来源
                .allowedOrigins("*")
                .allowedMethods("POST","GET","PUT","DELETE")
                .allowedHeaders("Content-Type","x-requested-with","X-Custom-Header")
                //时效
                .maxAge(1800);
    }
}
