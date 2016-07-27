package com.lrest.server.api;

import com.lrest.server.services.Config;
import com.lrest.server.services.SystemManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by acans on 16/6/23.
 */
@Path("/")
public class app {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String get() {


       return "welcome to LrestServer demo"+" mysql="+ Config.use_mysql +" redis="+Config.use_redis +" "+ SystemManager.dump();


    }
}
