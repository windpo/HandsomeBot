package com.windpo.handsomebot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
class HandsomeBotApplicationTests {
@Resource
private RedisTemplate<String,Object> redisTemplate;
@Resource
private StringRedisTemplate stringRedisTemplate;
    @Test
    void set() {
//        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
//        opsForValue.set("food:4","牛肉面");
        stringRedisTemplate.opsForSet().add("food","牛肉面");
    }
    @Test
    void get(){
//        ValueOperations ops = redisTemplate.opsForValue();
//        log.info(ops.get("food:4").toString());
//        System.out.println(stringRedisTemplate.opsForSet().randomMember("food"));
        System.out.println(redisTemplate.opsForSet().randomMember("food").toString());
    }

}
