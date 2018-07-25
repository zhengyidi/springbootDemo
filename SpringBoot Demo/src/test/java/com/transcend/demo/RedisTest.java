package com.transcend.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Logger log = LogManager.getLogger(RedisTest.class);

    @Test
    public void test1() throws InterruptedException {
//        stringRedisTemplate.opsForValue().set("a","a", 6, TimeUnit.SECONDS);
//        System.err.println("**************************************");
//        System.err.println(stringRedisTemplate.opsForValue().get("a"));
//        log.error(stringRedisTemplate.opsForValue().get("a"));
//        Thread.sleep(7000);
        System.err.println("**************************************");
        System.err.println(stringRedisTemplate.opsForValue().get("a"));
//        log.error(stringRedisTemplate.opsForValue().get("a"));

    }


}
