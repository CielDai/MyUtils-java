package com.sakura;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Resource
    private RedissonClient redissonClient;


    public static void main( String[] args ) {

        SpringApplication springApplication = new SpringApplication(App.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        RMap<Object, Object> map1 = redissonClient.getMap("map1", StringCodec.INSTANCE);
        System.out.println(map1);
    }
}
