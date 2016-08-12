package com.lrest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by acans on 16/6/21.
 */

public class MemorySessionManager implements SessionManagerInteface{
    private static  final Logger log = LoggerFactory.getLogger("MemorySessionManager");

    private   HashMap<String, String> session = new HashMap<String, String>();
    public MemorySessionManager() {

        log.info("MemorySessionManager init");

    }


    private static String genUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    @Override
    public  String createSID(String _token,String _uid){



        String sid=genUUID();
        String info=_token+" "+_uid+" "+System.currentTimeMillis();
        log.info(String.format("sid {%s} {%s}",sid,info));
        session.put(sid,info);


        for( Map.Entry<String, String> entry: session.entrySet()) {
            log.debug(String.format("key {%s} {%s}",entry.getKey(),entry.getValue()));
        }

        return sid;
    }


    @Override
    public  String getSID(String _sid){

        for( Map.Entry<String, String> entry: session.entrySet()) {
            log.debug(String.format("key {%s} {%s} {}",entry.getKey(),entry.getValue()),_sid);
        }

        String info=session.get(_sid);



        if (info==null){
            return null;
        }



        log.debug(String.format("info  %s %s %s %d %d %d",_sid,info
                ,info.substring(info.lastIndexOf(" ")+1,info.length())
                ,System.currentTimeMillis()
                ,System.currentTimeMillis()-Long.parseLong(info.substring(info.lastIndexOf(" ")+1,info.length()))
                , Config.session_timeout)

        );


        if (System.currentTimeMillis()-Long.parseLong(info.substring(info.lastIndexOf(" ")+1,info.length()))> Config.session_timeout*1000){

            session.remove(_sid);
            return  null;
        }


        return info;



    }
    @Override
    public void delSID(String _sid){

        session.remove(_sid);

    }


}
