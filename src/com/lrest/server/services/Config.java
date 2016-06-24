package com.lrest.server.services;

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
    public static int sesssion_timeout;

    public static int use_mysql;
    public static String mysql_jdbcurl;
    public static String mysql_user;
    public static String mysql_password;

    public static int use_redis;
    public static String redis_ip;
    public static int redis_port;


}
