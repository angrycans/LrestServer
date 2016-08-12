package com.lrest.server.services;

import javax.inject.Singleton;

/**
 * Created by acans on 16/6/21.
 */
@Singleton
public class SystemManager {

    public static  int SYSCODE=0;
    private static  int REDISCODE=0;
    private static  int MYSQLCODE=0;
    public static  String LASTERR;

    public SystemManager(){
        SYSCODE=1;
    }

    public static  void putCode(int _e,int _type,String _desc){
        if (_type==1){
            MYSQLCODE=_e;
        }else if (_type==2){
            REDISCODE=_e;
        }

        LASTERR=_desc;


        if (_e>1){
            SYSCODE=-1;
        }


    }


    public static  String dump(){
        return "SYSCODE="+SYSCODE+" REDISCODE="+REDISCODE+" MYSQLCODE="+MYSQLCODE+" LASTERR="+LASTERR;
    }

}
