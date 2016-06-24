package com.lrest.server.controller;

/**
 * Created by acans on 16/6/21.
 */

import com.google.gson.Gson;
import com.lrest.server.jooqmodel.tables.pojos.Tusers;
import com.lrest.server.services.Config;
import com.lrest.server.services.DB;
import com.lrest.server.services.MemorySessionManager;
import com.lrest.server.services.RedisSessionManager;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

@Path("/demo")
public class DemoController extends BaseController{
    private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());


    @GET

    public String get() {

     return "demo";
    }
}
