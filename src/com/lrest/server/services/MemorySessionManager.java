package com.lrest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by acans on 16/6/21.
 */

public class MemorySessionManager implements SessionManager{
    private static  final Logger log = LoggerFactory.getLogger("MemorySessionManager");

    public static  HashMap<String, String> session = new HashMap<String, String>();
    public MemorySessionManager() {

    }




    private static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @Override
    public  String createSID(String _token,String _uid){

        String sid=genUUID();
        String info=_token+" "+_uid+" "+System.currentTimeMillis();
        session.put(sid,info);

        return sid;
    }


    @Override
    public  String getSID(String _sid){

        String info=session.get(_sid);

        log.info(info.substring(info.lastIndexOf(" "),info.length()-1));

        if (System.currentTimeMillis()-Long.parseLong(info.substring(info.lastIndexOf(" "),info.length()-1))>Config.sesssion_timeout){
            return  null;
        }


        return info;



    }
    @Override
    public void delSID(String _sid){

        session.remove(_sid);

    }


}
