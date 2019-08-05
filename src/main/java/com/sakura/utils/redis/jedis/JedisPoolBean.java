//package com.sakura.utils.redis.jedis;
//
//import org.springframework.data.redis.core.StringRedisTemplate;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Description:
// *
// * @author sakura
// * <p>
// * Date: 2019-04-08 10:06 PM
// * <p>
// * Created with IntelliJ IDEA.
// */
//public class JedisPoolBean {
//
//    private final String HOST = "127.0.0.1";
//    private final int PORT = 6379;
//    private final int TIMEOUT = 3000;
//    private final String PASSWORD = null;
//    private final int DATABASE = 0;
//
//
//    public JedisPool createJedisPool() {
//
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(10);
//
//        JedisPool jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD, DATABASE);
//
//        return jedisPool;
//    }
//
//
//    public static void main(String[] args) {
//        JedisPool jedisPool = new JedisPoolBean().createJedisPool();
//        String s = jedisPool.getResource().get("spring.boot.redis.test");
//        System.out.println(s);
//
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//
//    }
//}
