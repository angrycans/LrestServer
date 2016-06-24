package com.lrest.server;

/**
 * Created by angrycans on 15/7/7.
 */

import com.lrest.server.services.Config;
import com.lrest.server.services.MemorySessionManager;
import com.lrest.server.services.RedisSessionManager;
import com.lrest.server.services.SystemManager;
import com.mysql.jdbc.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


@PreMatching
public class RequestFilter implements ContainerRequestFilter
{

    private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private RedisSessionManager redisSessionManager;

    @Inject
    private SystemManager systemManager;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {



        MultivaluedMap<String, String> requestheaders = requestContext.getHeaders();
        System.out.println("Executing REST RequestFilter header >"+requestContext.getUriInfo().getPath());
        System.out.println("Executing REST RequestFilter getMethod >"+requestContext.getMethod());

        if (systemManager.SYSCODE!=1){
            log.error("System error");
            requestContext.abortWith(Response.status(Response.Status.EXPECTATION_FAILED).entity(systemManager.LASTERR).build());
            return;
        }

        if (requestContext.getMethod().equalsIgnoreCase("options")) {
            requestContext.abortWith(Response.ok().build());
        }


        String sid = requestContext.getHeaderString("sessionid");
        if (StringUtils.isNullOrEmpty(sid)){
            if (requestContext.getUriInfo().getPath().equalsIgnoreCase("login")){
                //go to login  auth sid
            }else{
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(systemManager.LASTERR).build());
                return;
            }

        }else{
            //auth sid expired
            String authsid;
            if (Config.use_redis==1){
                authsid=redisSessionManager.getSID(sid);
            }else{
                MemorySessionManager memorySessionManager=new MemorySessionManager();
                authsid=memorySessionManager.getSID(sid);
            }

            if (StringUtils.isNullOrEmpty(authsid)){
                requestContext.abortWith(Response.status(Response.Status.REQUEST_TIMEOUT).build());
                return;
            }

        }

        //RedisSessionManager rsm=new RedisSessionManager();
        //log.info(rsm.createSID("1",2));
//
//        Iterator it1 = requestheaders.entrySet().iterator();
//        while (it1.hasNext()) {
//            Map.Entry pairs = (Map.Entry) it1.next();
//            System.out.println("  " + pairs.getKey() + " = " + pairs.getValue());
//        }



        /*

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        System.out.println("Executing REST response header>");

        Iterator it2 = headers.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pairs = (Map.Entry) it2.next();
            System.out.println("  " + pairs.getKey() + " = " + pairs.getValue());
        }


        System.out.println("Inject sessionService 0"+sessionService);
        System.out.println("Inject sessionService 1"+sessionService.get("randmonsid"));
*/
//        if (requestheaders.get("session-id")==null){
//            sessionService.set("randmonsid", new SessionInfo("aaa"));
//        }
    }



}


