package com.windpo.handsomebot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 风之诗
 * @version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/daily")
public class DailyFunctionController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 将食物的名称添加到redis数据库中
     */
    @PostMapping("/addFood")
    String addFood(@RequestParam("foodName")String foodName){
        String tableName="food";
        stringRedisTemplate.opsForSet().add(tableName,foodName);
        return "添加成功!";
    }
    /**
     * 从redis中随机获取食物的名称（使用set结构）
     */
    @GetMapping("/getFood")
    String getFood(){
        String tableName="food";
        return stringRedisTemplate.opsForSet().randomMember(tableName);
    }
    /**
     * 将喝的东西添加到redis数据库中
     */
    @PostMapping("/addDrink")
    String addDrink(String drinkName){
        String tableName="drink";
        stringRedisTemplate.opsForSet().add(tableName,drinkName);
        return "添加成功!";
    }
    /**
     * 从redis中随机获取饮料的名称
     */
    @GetMapping("/getDrink")
    String getDrink(){
        String tableName="drink";
        return stringRedisTemplate.opsForSet().randomMember(tableName);
    }
}
