package com.lrest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.inject.Singleton;
import java.util.UUID;

/**
 * Created by acans on 16/6/21.
 */

public class RedisSessionManager implements SessionManager{
    private static  final Logger log = LoggerFactory.getLogger("RedisSessionManager");
    //redis schema
    //LSESSION:ID:INFO  {token userid last_at} expired

    public static final String SESSION_ID_PREFIX = "LSESSION:";

    //Session最大更新间隔时间
    private static int expirationUpdateInterval;
    //Session过期时间
    private static int sessionTimeOut;

    private static JedisPool jedisPool;

    public RedisSessionManager() {
        if (Config.use_redis==1) {
            this.expirationUpdateInterval = 60 * 1000;
            this.sessionTimeOut = 1 * 60 * 1000;
            if (jedisPool == null) {
                createJedisPool(Config.redis_ip, Config.redis_port);
            }

        }else{
            SystemManager.putCode(1,2,"");
        }
    }


    public void createJedisPool(String redisIp,int redisPort){

        try {
            JedisPoolConfig config = new JedisPoolConfig();
            jedisPool = new JedisPool(config, redisIp, redisPort, 2000);

            jedisPool.getResource();
            SystemManager.putCode(1,2,"");
            log.info("--------------RedisSessionManager createJedisPool ok----------------");
        }catch (Exception e){
            //e.printStackTrace();
            log.error("--------------RedisSessionManager init error----------------");
        }


        //Jedis redis=jedisPool.getResource();


    }

    private static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @Override
    public  String createSID(String _token,String _uid){

        String sid=genUUID();
        String info=_token+" "+_uid+" "+System.currentTimeMillis();

        jedisPool.getResource().setex(SESSION_ID_PREFIX+sid+":INFO",sessionTimeOut,info);
        log.info(SESSION_ID_PREFIX+sid+":INFO"+" "+info);
        return sid;
    }


    @Override
    public  String getSID(String _sid){

        String info=jedisPool.getResource().get(SESSION_ID_PREFIX+_sid+":INFO");

        if (info==null){
            return null;
        }else{
            log.info(info.substring(info.lastIndexOf(" "),info.length()-1));
            return info;
        }



    }
    @Override
    public void delSID(String _sid){

        jedisPool.getResource().del(SESSION_ID_PREFIX+_sid+":INFO");

    }


}
