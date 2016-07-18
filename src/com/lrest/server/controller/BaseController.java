package com.lrest.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lrest.server.services.RedisSessionManager;
import com.lrest.server.services.SessionManager;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by acans on 16/6/23.
 */
public class BaseController {


    @Context
    HttpServletRequest req;
    @Context
    HttpServletResponse res;


    public class ResponseData {
        public e e=new e();

        class e {

            public int code;

            public String desc;
        }
        public String data;

        public  ResponseData(){
            e.code=0;
            e.desc="";
            data="";
        }

        public  ResponseData(int _e,String _desc,String _data){
            e.code=_e;
            e.desc=_desc;
            data=_data;
        }

        public  ResponseData(String _data){
            e.code=0;
            e.desc="";
            data=_data;
        }
    }


    public String errorRespond() {


        return new Gson().toJson(new ResponseData(-1,"",""));
    }

    public String errorRespond(int _e,String _err) {


        return new Gson().toJson(new ResponseData(_e,_err,""));
    }

    public String successRespond() {

/*
        Gson g= new GsonBuilder().serializeNulls().create();

        return g.toJson(new ResponseData());
*/

        return new Gson().toJson(new ResponseData());


        //return "{e:{code:0,desc:\"\"}}";
    }

    public String successRespond( int _code,String _data) {
        return new Gson().toJson(new ResponseData(_code,"",_data));
    }

    public String successRespond(String _data) {
        return new Gson().toJson(new ResponseData(0,"",_data));
    }


}
