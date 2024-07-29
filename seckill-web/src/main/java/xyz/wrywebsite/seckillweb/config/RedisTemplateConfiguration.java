/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.seckillweb.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author wry
 * @version 1.0
 * @classname RedisClientConfiguration
 * @description
 * @since 1.0
 */
@Configuration
public class RedisTemplateConfiguration {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {


        //用于处理字符串的序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //定义RedisTemplate
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        //使用处理字符串的序列化器处理key
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        //使用字符串处理器处理value
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);

        //设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //关闭事务
        redisTemplate.setEnableTransactionSupport(false);

        return redisTemplate;
    }
}
