package com.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//    @Resource
//    private RedisUtil redisUtil;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Spring Boot 应用启动后执行该方法
        System.out.println("Spring Boot 应用启动...");

//        // 将数据存入 Redis 中
//        redisUtil.set("key", "value", 300); //设置key为value，有效期为300秒
//        System.out.println("将数据存入 Redis 中...");
    }
}