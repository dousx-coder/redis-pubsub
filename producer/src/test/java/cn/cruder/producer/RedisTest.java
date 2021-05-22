package cn.cruder.producer;

import cn.cruder.producer.util.RedisUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * @Author Cruder
 * @Date 2021/5/21 0021 22:00
 */
@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void pubsubTest() {

        String msg1 = "A:" + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
        String channel1 = "topic_config_monitor1";
        stringRedisTemplate.convertAndSend(channel1, msg1);
        log.info("channel={},message={}", channel1, msg1);



        String msg2 = "B:" + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
        String channel2 = "topic_config_monitor1";
        stringRedisTemplate.convertAndSend(channel2, msg2);
        log.info("channel={},message={}", channel2, msg2);

    }
}
