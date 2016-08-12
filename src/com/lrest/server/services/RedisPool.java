package com.lrest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by angrycans on 16/7/27.
 */
public class RedisPool {
    private static  final Logger log = LoggerFactory.getLogger("RedisPool");
    private static JedisPool jedisPool;



    private static RedisPool instance = new RedisPool();
    public static RedisPool getInsatnce(){
        return instance;
    }



    private RedisPool(){


        if (Config.use_redis==1) {
            log.info("RedisPool Singleton create");

            if (jedisPool == null) {
                createJedisPool(Config.redis_ip, Config.redis_port);
            }

        }

    }

    public void createJedisPool(String redisIp,int redisPort){

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            jedisPool = new JedisPool(config, redisIp, redisPort, 2000);

            jedisPool.getResource();
            SystemManager.putCode(1,2,"");
            log.info("-------------- createJedisPool ok----------------");
        }catch (Exception e){
            //e.printStackTrace();
            SystemManager.putCode(-1,2,"Redis connect error");
            log.error("-------------- Redis init error----------------"+ SystemManager.dump());
        }


        //Jedis redis=jedisPool.getResource();


    }


    public static Jedis getRedis(){
        if (jedisPool!=null){
            return jedisPool.getResource();
        }else{
            return null;
        }

    }

}
