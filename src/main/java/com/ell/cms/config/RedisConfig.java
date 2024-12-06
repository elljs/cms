package com.ell.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RedisConfig {
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 反序列化时候遇到不匹配的属性并不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 序列化时候遇到空对象不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化的时候如果是无效子类型,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        // 不使用默认的dateTime进行序列化,
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        // 使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
        objectMapper.registerModule(new JavaTimeModule());
        // 启用反序列化所需的类型信息,在属性中添加@class
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY);
        // 配置null值的序列化器
        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(objectMapper, null);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
            RedisSerializer<Object> redisSerializer) {
        // 创建一个RedisTemplate实例
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        // 设置Redis连接工厂，这是RedisTemplate与Redis服务器进行通信所必需的
        template.setConnectionFactory(redisConnectionFactory);

        // 设置默认的序列化器，用于将对象序列化为字节数组，以便存储在Redis中
        template.setDefaultSerializer(redisSerializer);

        // 设置值对象的序列化器，与setDefaultSerializer类似，但仅针对值对象
        template.setValueSerializer(redisSerializer);

        // 设置哈希值对象的序列化器，当使用哈希数据结构时，这个序列化器将用于序列化哈希中的值
        template.setHashValueSerializer(redisSerializer);

        // 设置键的序列化器，这里使用StringRedisSerializer来确保键是以UTF-8编码的字符串形式存储的
        template.setKeySerializer(StringRedisSerializer.UTF_8);

        // 设置哈希键的序列化器，与setKeySerializer类似，但仅针对哈希数据结构中的键
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);

        // 调用afterPropertiesSet方法，这个方法是在所有的bean属性被Spring容器设置之后调用的
        // 它用于执行一些初始化工作，比如检查配置是否正确等
        template.afterPropertiesSet();

        // 返回配置好的RedisTemplate实例
        return template;
    }
}
