package cn.cruder.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * Redis 发布订阅 (pub/sub)
 *
 * @Author Cruder
 * @Date 2021/5/22 0022 8:41
 */
@Component
@Slf4j
public class RedisPubsubListener extends MessageListenerAdapter {


    @Bean
    @ConditionalOnBean(MessageListenerAdapter.class)
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter redisListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 添加要监听的队列topic_config_monitor
        container.addMessageListener(redisListener, new PatternTopic("topic_config_monitor1"));
        container.addMessageListener(redisListener, new PatternTopic("topic_config_monitor2"));
        return container;
    }

    /**
     * redis监听事件
     *
     * @param message 消息
     * @param pattern 消息类型(队列)
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        log.info("redisListener: channel={} , message={}", channel, message.toString());
    }
}
