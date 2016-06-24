package com.lrest.server;

/**
 * Created by angrycans on 15/7/7.
 */

import javax.inject.Inject;
import javax.ws.rs.container.*;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

@PreMatching
public class ResponseFilter implements ContainerResponseFilter
{




    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
//
//        MultivaluedMap<String, String> requestheaders = requestContext.getHeaders();
//        System.out.println("Executing REST RequestFilter header >"+requestContext.getUriInfo().getPath());
//
//        Iterator it1 = requestheaders.entrySet().iterator();
//        while (it1.hasNext()) {
//            Map.Entry pairs = (Map.Entry) it1.next();
//            System.out.println("  " + pairs.getKey() + " = " + pairs.getValue());
//        }

        MultivaluedMap<String, Object> responseheaders = responseContext.getHeaders();
//        System.out.println("Executing REST ResponseFilter header >");
//
//        Iterator it2 = responseheaders.entrySet().iterator();
//        while (it2.hasNext()) {
//            Map.Entry pairs = (Map.Entry) it2.next();
//            System.out.println("  " + pairs.getKey() + " = " + pairs.getValue());
//        }


        responseheaders.add("Access-Control-Allow-Origin", "*");
        //headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org
        responseheaders.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        responseheaders.add("Access-Control-Allow-Headers", " Content-Type, sessionid");




    }



}


