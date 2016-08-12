package com.lrest.server.controller;

/**
 * Created by acans on 16/6/21.
 */

import com.lrest.server.services.SessionManager;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/login")
public class LoginController extends BaseController {
    private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

//    @Inject
//    private SessionManager sessionManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String get() {

        String  _token= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String _uid="test";
        String sid;

        sid= SessionManager.getInstance().createSID(_token,_uid);



        res.setHeader("sessionid",sid);


        return success(sid);

    }
}
