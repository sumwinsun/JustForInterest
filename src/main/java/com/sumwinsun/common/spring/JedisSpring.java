package com.sumwinsun.common.spring;

import com.alibaba.druid.util.StringUtils;
import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class JedisSpring {

    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，
    //则此时pool的状态为exhausted（耗尽）
    private static int MAX_TOTAL = 1024;

    //控制一个pool最多有多少个状态为idle（空闲的）的jedis实例，默认为8
    private static int MAX_IDLE = 20;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间,则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
    private static boolean TEST_ON_BORROW = false;

    //    redis的密码
    private static String AUTH = "";

    private final JedisPool jedisPool;

    //切片连接池
    private static ShardedJedisPool shardedJedisPool;

    public JedisSpring(String ip, int port, String auth) {
        if (!StringUtils.isEmpty(auth)) AUTH = auth;
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(MAX_TOTAL);
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
//        config.setMaxWait(MAX_WAIT);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        JedisShardInfo jedisInfo_1 = new JedisShardInfo(ip, port);
        //设置访问密码
        jedisInfo_1.setPassword(auth);
        list.add(jedisInfo_1);
            /*------------------*/

        //添加其它redis服务器

			/*------------------*/

        //分布式存储
        shardedJedisPool = new ShardedJedisPool(config, list);
        this.jedisPool = new JedisPool(config, ip, port, 60000);

    }

    /**
     * 获取jedis资源
     *
     * @return
     */
    public Jedis getJedisSource() {
        Jedis jedis = jedisPool.getResource();
        if (!StringUtils.isEmpty(AUTH))
            jedis.auth(AUTH);
        return jedis;
    }

}
