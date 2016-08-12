package com.lrest.server.controller;

/**
 * Created by acans on 16/6/21.
 */

import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/demo2")
public class DemoController extends BaseController {
    private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());


    @GET

    public String get() {

     return "demo";
    }
}
