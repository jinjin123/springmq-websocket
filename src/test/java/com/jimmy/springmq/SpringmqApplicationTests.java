package com.jimmy.springmq;

import com.jimmy.springmq.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmqApplicationTests {

    @Autowired
    private RedisService redisService;
    @Test
    public void contextLoads() throws Exception{
        redisService.set("a","a");
    }

}
