package com.gdb.doordash.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: Mr.Gui
 * @program: doordash
 * @create: 2024-05-20 12:39
 * @description: redis的相关配置
 **/

@Configuration
@Slf4j
public class RedisConfiguration {
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 我们为了自己开发方便，一般都是直接使用<String, Object>
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // Json 序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key 采用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // value 序列化方式采用 jackson
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        // hash 的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // hash 的value也采用jackson的序列化方式
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
