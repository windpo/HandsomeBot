package com.windpo.handsomebot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 风之诗
 * @version 1.0
 */
@Service
@Slf4j
public class DailyFunctionServiceImpl {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


}
