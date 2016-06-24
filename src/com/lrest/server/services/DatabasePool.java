package com.lrest.server.services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by acans on 16/6/17.
 */
public class DatabasePool {
    private HikariDataSource database;
    private HikariConfig databaseConfiguration;

    public boolean getStoragePooling()
    {
        try
        {
            this.databaseConfiguration = new HikariConfig();
            this.databaseConfiguration.setMaximumPoolSize(20);
            this.databaseConfiguration.setDriverClassName("com.mysql.jdbc.Driver");
            //this.databaseConfiguration.setJdbcUrl("jdbc:mysql://192.168.2.16:3306/testdb?user=root&password=123456");
            this.databaseConfiguration.setJdbcUrl(Config.mysql_jdbcurl);
            this.databaseConfiguration.setUsername(Config.mysql_user);
            this.databaseConfiguration.setPassword(Config.mysql_password);
            this.databaseConfiguration.setAutoCommit(true);
            this.databaseConfiguration.setMaxLifetime(120000000L);
            this.databaseConfiguration.setConnectionTestQuery("SELECT 1");
            this.database = new HikariDataSource(this.databaseConfiguration);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HikariDataSource getDatabase()
    {
        return this.database;
    }
}
