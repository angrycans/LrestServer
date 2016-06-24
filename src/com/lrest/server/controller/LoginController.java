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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
        import javax.ws.rs.GET;
        import javax.ws.rs.Path;
        import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
        import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

@Path("/login")
public class LoginController extends BaseController{
    private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    private RedisSessionManager redisSessionManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String get() {

        String  _token= UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String _uid="test";
        String sid;

        if (Config.use_redis==1){
            sid=redisSessionManager.createSID(_token,_uid);
        }else{
            MemorySessionManager memorySessionManager=new MemorySessionManager();
            sid=memorySessionManager.createSID(_token,_uid);
        }

        res.setHeader("sessionid",sid);


        ///test db
////http://www.jooq.org/doc/2.5/manual/sql-execution/fetching/pojos/ example
        if (Config.use_mysql==1){
            try(Connection conn=DB.getConnection();
                DSLContext create = DSL.using(conn , SQLDialect.MYSQL))  {

                List<Tusers> TusersList=create.select().from("tusers").where("id=1").fetchInto(Tusers.class);
                Gson gson = new Gson();
                if (TusersList.size()>0) {
                    log.info("1 " + gson.toJson(TusersList.get(0)));
                }
                Tusers tuser=create.select().from("tusers").where("id=2").limit(1).fetchOneInto(Tusers.class);
                log.info("2 "+gson.toJson(tuser));


                Result<Record> result = create.select().from("tusers").limit(1).fetch();

                for (Record r : result) {

                    System.out.println("id: " + r.get("id"));


                   // TusersRecord r=new


                }


                log.info("3 "+result.formatJSON());


                return successRespond(null);

            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }else{
            return successRespond(null);
        }



    }
}
