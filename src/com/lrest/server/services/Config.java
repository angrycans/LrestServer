package com.lrest.server.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by acans on 16/6/23.
 */

/*
use_mysql=0
mysql_jdbcurl="jdbc:mysql://192.168.2.16:3306/testdb"
mysql_user="root"
mysql_password="123456"



#redis session use expired feature memsession not expried   | 0 off 1 on
use_redis=0
redis_ip="127.0.0.1"
redis_port=6379


 */
public class Config {
    private static  final Logger log = LoggerFactory.getLogger("Config");
    public static int session_timeout;

    public static int use_mysql;
    public static String mysql_jdbcurl;
    public static String mysql_user;
    public static String mysql_password;

    public static int use_redis;
    public static String redis_ip;
    public static int redis_port;


    private static Config instance = new Config();
    public static Config getInsatnce(){
        return instance;
    }



    private Config(){
        log.info("Config instance create");
        ResourceBundle resourceBoudle = null;

        try {
            resourceBoudle = new PropertyResourceBundle(
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream(
                                    "config/config.properties"));

            //log.debug("resouce mysql="+resourceBoudle.getString("mysql"));

            this.session_timeout=Integer.parseInt(resourceBoudle.getString("session_timeout"));

            this.use_mysql=Integer.parseInt(resourceBoudle.getString("use_mysql"));
            this.mysql_jdbcurl=resourceBoudle.getString("mysql_jdbcurl");
            this.mysql_user=resourceBoudle.getString("mysql_user");
            this.mysql_password=resourceBoudle.getString("mysql_password");

            this.use_redis=Integer.parseInt(resourceBoudle.getString("use_redis"));
            this.redis_ip=resourceBoudle.getString("redis_ip");
            this.redis_port=Integer.parseInt(resourceBoudle.getString("redis_port"));

        } catch (Exception e) {
            e.printStackTrace();
            // LOGGER.error("Error loading messages properties", ex);
        }

    }

}
