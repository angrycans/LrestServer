package com.lrest.server.controller;

import com.google.gson.JsonObject;
import com.lrest.server.services.RedisSessionManager;
import com.lrest.server.services.SessionManager;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 * Created by acans on 16/6/23.
 */
public class BaseController {


    @Context
    HttpServletRequest req;
    @Context
    HttpServletResponse res;



    public String errorRespond(int ecode,String err) {


        return "{e:{code:0,desc:1}}";
    }


    public String successRespond(JsonObject rst) {
        return "{e:{code:0,desc:1}}";
    }
}
