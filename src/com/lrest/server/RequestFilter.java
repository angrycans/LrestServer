package com.lrest.server;

/**
 * Created by angrycans on 15/7/7.
 */

import com.lrest.server.services.session.SessionManager;
import com.lrest.server.services.SystemManager;
import com.mysql.jdbc.StringUtils;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;


@PreMatching
public class RequestFilter implements ContainerRequestFilter
{

    private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    //@Inject
    //private SessionManager sessionManager;

    @Inject
    private SystemManager systemManager;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {



        MultivaluedMap<String, String> requestheaders = requestContext.getHeaders();
        log.debug("Executing REST RequestFilter getAbsolutePath >"+requestContext.getUriInfo().getAbsolutePath());
        log.debug("Executing REST RequestFilter header >"+requestContext.getUriInfo().getPath());
        log.debug("Executing REST RequestFilter getMethod >"+requestContext.getMethod());

        if (systemManager.SYSCODE!=1){
            log.error("System error "+systemManager.dump());
            requestContext.abortWith(Response.status(Response.Status.EXPECTATION_FAILED).entity(systemManager.LASTERR).build());
            return;
        }

        if (requestContext.getMethod().equalsIgnoreCase("options")) {
            requestContext.abortWith(Response.ok().build());
            return;
        }


        ArrayList<String> ignorelist=new ArrayList<String>();
        ignorelist.add("/");
        ignorelist.add("login/");
        ignorelist.add("wx/");
        ignorelist.add("demo/");
        ignorelist.add("nl/");


        String urlpath=requestContext.getUriInfo().getPath()+"/";
        for (int i = 0; i < ignorelist.size(); i++) {
            //log.debug(" "+urlpath.substring(0,urlpath.indexOf("/")+1)+" "+ignorelist.get(i));
            if (urlpath.substring(0,urlpath.indexOf("/")+1).equalsIgnoreCase(ignorelist.get(i))){

                log.info("no login enter");
                return;
            }
        }



        String sid = requestContext.getHeaderString("sessionid");
        if (StringUtils.isNullOrEmpty(sid)){

            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(systemManager.LASTERR).build());
            return;

        }else{
            //auth sid expired

            String authsid= SessionManager.getInstance().getSID(sid);

            if (StringUtils.isNullOrEmpty(authsid)){
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                return;
            }

        }



        /*

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        System.out.println("Executing REST response header>");

        Iterator it2 = headers.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry pairs = (Map.Entry) it2.next();
            System.out.println("  " + pairs.getKey() + " = " + pairs.getValue());
        }

*/

    }



}


