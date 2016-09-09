package com.lrest.server.services.session;

import com.lrest.server.services.Config;
import com.lrest.server.services.RedisPool;
import com.lrest.server.services.SystemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * Created by acans on 16/6/21.
 */

public class RedisSessionManager implements SessionManagerInteface {
    private static  final Logger log = LoggerFactory.getLogger("RedisSessionManager");
    //redis schema
    //LSESSION:ID:INFO  {token userid last_at} expired

    public static final String SESSION_ID_PREFIX = "LSESSION:";

    //Session最大更新间隔时间
    private static int expirationUpdateInterval;
    //Session过期时间
    //private static int sessionTimeOut;

    //private static JedisPool jedisPool;

    public RedisSessionManager() {

        log.info("RedisSessionManager init");
        if (Config.use_redis==1) {
            this.expirationUpdateInterval = 30 * 1000;
            //this.sessionTimeOut = 1 * 60 * 1000;


        }else{
            SystemManager.putCode(1,2,"");
        }
    }




    private static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @Override
    public  String createSID(String _token,String _uid){

        try (Jedis jedis= RedisPool.getRedis()) {


            log.debug(String.format("createSID %s",_token));

            String sid=genUUID();
            String info=_token+" "+_uid+" "+System.currentTimeMillis();

            jedis.setex(SESSION_ID_PREFIX+sid+":INFO", Config.session_timeout*1000,info);
            log.debug(SESSION_ID_PREFIX+sid+":INFO"+" "+info);
            return sid;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public  String getSID(String _sid){
        try (Jedis jedis= RedisPool.getRedis()) {

        String info=jedis.get(SESSION_ID_PREFIX+_sid+":INFO");

        if (info==null){
            return null;
        }else{
            log.info(info);
            log.info(info.substring(0,info.lastIndexOf(" ")));
            log.info(info.substring(info.lastIndexOf(" ")+1,info.length()));
            //String info=_token+" "+_uid+" "+System.currentTimeMillis();

            if (System.currentTimeMillis()-Long.parseLong(info.substring(info.lastIndexOf(" ")+1,info.length()))<expirationUpdateInterval){
                log.info("diff time "+(System.currentTimeMillis()-Long.parseLong(info.substring(info.lastIndexOf(" ")+1,info.length()))));
                return info;
            }else{
                log.debug(String.format("getSID  %s",1));
                String newinfo=info.substring(0,info.lastIndexOf(" "))+" "+System.currentTimeMillis();
                jedis.setex(SESSION_ID_PREFIX+_sid+":INFO", Config.session_timeout*1000,newinfo);
                return newinfo;
            }

        }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
    @Override
    public void delSID(String _sid){
        try (Jedis jedis= RedisPool.getRedis()) {
            jedis.del(SESSION_ID_PREFIX+_sid+":INFO");
    } catch (Exception e) {
        e.printStackTrace();

    }

    }


}
