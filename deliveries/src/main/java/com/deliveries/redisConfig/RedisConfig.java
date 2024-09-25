package com.deliveries.redisConfig;

import com.deliveries.dtos.OrderCurrentLocationDTO;
import com.deliveries.service.DriversDeliveringOrdersLocationSubscriber;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Configuration
public class RedisConfig {


    @Value("${topics.drivers-delivering-order-location}")
    private String driversDeliveringAnOrderTopic;

    @Bean
    public RedisConnectionFactory connectionFactory(RedisProperties redisProperties) {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED) //write to master, read from replicas
                .build();

        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        clusterConfig.setPassword(RedisPassword.of(redisProperties.getPassword()));
        return new LettuceConnectionFactory(clusterConfig, clientConfig);
    }

    @Bean
    public RedisTemplate<String, OrderCurrentLocationDTO> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, OrderCurrentLocationDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());// json serializer for values
        template.setKeySerializer(new StringRedisSerializer()); // string serializers for keys
        return template;
    }

    //pub sub configurations

    @Bean
    public MessageListenerAdapter messageListenerAdapter(DriversDeliveringOrdersLocationSubscriber listener) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener, "handleMessage");

        // JSON deserialization for message content
        adapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return adapter;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter listener) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listener, ChannelTopic.of(driversDeliveringAnOrderTopic));
        return container;
    }
}