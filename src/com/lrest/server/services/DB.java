package com.lrest.server.services;

/**
 * Created by acans on 16/6/17.
 */

import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.sql.*;

@Singleton
public class DB {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    private static HikariDataSource dataSource;
    //static private   Connection driverConnection;
    //private Statement driverStatement;


    public DB(){
        dbinit();
    }


    public void dbinit()
    {
        log.debug("init db");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            DatabasePool Pooling = new DatabasePool();
            if (!Pooling.getStoragePooling())
            {

                log.info("Pooling.getStoragePooling init error");
                return;
            }
            this.dataSource = Pooling.getDatabase();

            //this.driverConnection = this.dataSource.getConnection();
           // this.driverStatement = this.driverConnection.createStatement();
            SystemManager.putCode(1,1,"");

            log.debug("db init ok");
        }

        catch (Exception e)
        {
            e.printStackTrace();

            SystemManager.putCode(-1,1,"mysql error");
            log.debug("init db error");
        }
    }

    public static  Connection getConnection(){
        try {

            return dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public HikariDataSource getDataSource()
    {
        return this.dataSource;
    }

    public   void testDb(){
        try{
            //Connection conn;
            PreparedStatement ps;
            ResultSet rs;
            String sql="select id from T_USER limit 10";
            ps = DB.getConnection().prepareStatement(sql);

            rs=ps.executeQuery();
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            int count = rsmd.getColumnCount();// 字段数量
//            log.info("count=",count);
//           // String[] colNames = new String[count];
//            for (int i = 0; i < count; i++) {
//
//                log.info("rsmd=",rsmd.getColumnLabel(i).toLowerCase());
//            }

//            while (rs.next()) {
//                    log.info("rs="+rs.getInt("id"));
//
//            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
