package com.sakura.utils.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author sakura
 * <p>
 * Date: 2019-07-26 9:29 PM
 * <p>
 * Created with IntelliJ IDEA.
 */
public class RedissonConfig {

    public static RedissonClient redisson = null;

    public static void main(String[] args) throws IOException, InterruptedException {


        // 1. Create config object
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setPassword(null).setDatabase(0);

        // 2. Create Redisson instance

        // Sync and Async API
        RedissonConfig.redisson = Redisson.create(config);
        RBucket<Object> key1 = redisson.getBucket("key1", StringCodec.INSTANCE);
        key1.set("val1", 10, TimeUnit.MINUTES);

        // set
        RSet<Object> rset = redisson.getSet("set1", StringCodec.INSTANCE);
        rset.add("1");
        rset.add("2");
        rset.add("4");
        rset.add("3");
        rset.add("3");
        rset.add("3");

        RGeo<String> geo = redisson.getGeo("geo1", JsonJacksonCodec.INSTANCE);
        geo.add(new GeoEntry(13.361389, 38.115556, "Palermo"),
                new GeoEntry(15.087269, 37.502669, "Catania"));
        geo.addAsync(37.618423, 55.751244, "Moscow");

        Double dist = geo.dist("Palermo", "Catania", GeoUnit.KILOMETERS);
        System.out.println(dist + " km");

        // 获取成员位置
        Map<String, GeoPosition> positions = geo.pos("test2", "Palermo", "test3", "Catania", "test1");
        // 获取范围内的地点
        List<String> cities = geo.radius(15, 37, 200, GeoUnit.KILOMETERS);
        // 获取范围内的地点，带坐标
        Map<String, GeoPosition> citiesWithPositions = geo.radiusWithPosition(15, 37, 200, GeoUnit.KILOMETERS);

        System.out.println(2);
        RBitSet set = redisson.getBitSet("simpleBitset");
        set.set(0, true);
        set.set(1812, false);
        set.clear(0);
        set.xor("anotherBitset");

        boolean b = set.get(0);
        System.out.println(b);

        RTopic topic = redisson.getTopic("anyTopic");
        topic.addListener(String.class, (channel, msg) -> {
            System.out.println(channel + " : " + msg);
        });

        CompletableFuture.runAsync(() -> {
            // 在其他线程或JVM节点
            RTopic anyTopic = redisson.getTopic("anyTopic");
            long clientsReceivedMessage = anyTopic.publish(System.currentTimeMillis() + "");
            System.out.println("clientsReceivedMessage: " + clientsReceivedMessage);
            clientsReceivedMessage = anyTopic.publish(System.currentTimeMillis() + "");
            System.out.println("clientsReceivedMessage: " + clientsReceivedMessage);
        });
        System.out.println("================================================");

//        longAdder();

//        rateLimiter();

        map();
    }


    private static void map() {
        RMap<String, String> map = redisson.getMap("map1", StringCodec.INSTANCE);
        map.put("key1", "value1");

        RMapCache<Object, Object> mapCache = redisson.getMapCache("map2", StringCodec.INSTANCE);
        mapCache.put("field1", "value1", 100, TimeUnit.SECONDS);

    }


    private static void longAdder() {

        RLongAdder atomicLong = redisson.getLongAdder("myLongAdder");
        long sum = atomicLong.sum();
        atomicLong.add(12);
        sum = atomicLong.sum();
        atomicLong.increment();
        sum = atomicLong.sum();
        atomicLong.decrement();
        sum = atomicLong.sum();

        System.out.println(sum);
    }

    private static void rateLimiter() throws InterruptedException {

        RRateLimiter rateLimiter = redisson.getRateLimiter("myRateLimiter");
        // 初始化
        // 最大流速 = 每1秒钟产生10个令牌
        rateLimiter.trySetRate(RateType.PER_CLIENT, 5, 10, RateIntervalUnit.SECONDS);

        // 获取4个令牌
        boolean acquire = rateLimiter.tryAcquire(4);
        System.out.println("4=" + acquire);

        // 获取4个令牌
        acquire = rateLimiter.tryAcquire(4);
        System.out.println("4=" + acquire);
        // 获取4个令牌
        acquire = rateLimiter.tryAcquire(4);
        System.out.println("4=" + acquire);
        // 获取4个令牌
        acquire = rateLimiter.tryAcquire(4);
        System.out.println("4=" + acquire);
        // 获取4个令牌
        acquire = rateLimiter.tryAcquire(4);
        System.out.println("4=" + acquire);
        // 获取4个令牌
        acquire = rateLimiter.tryAcquire(4);
        System.out.println("4=" + acquire);
        // 尝试获取4个令牌，尝试等待时间为2秒钟
        acquire = rateLimiter.tryAcquire(4, 2, TimeUnit.SECONDS);
        System.out.println("4=" + acquire);

        rateLimiter.tryAcquireAsync(2, 2, TimeUnit.SECONDS);
        System.out.println(2);

        // 尝试获取1个令牌，等待时间不限
        rateLimiter.acquire();
        System.out.println(1);

        // 尝试获取1个令牌，等待时间不限
        RFuture<Void> future = rateLimiter.acquireAsync();
        System.out.println(future);

        boolean b = rateLimiter.tryAcquire();
        System.out.println(b);

    }



}
