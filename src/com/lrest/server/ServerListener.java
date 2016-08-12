package com.lrest.server;


import com.lrest.server.services.Config;
import com.lrest.server.services.DB;
import com.lrest.server.services.RedisPool;
import com.lrest.server.services.SystemManager;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ResourceBundle;

//import org.eclipse.persistence.annotations.CascadeOnDelete;

@WebListener
public class ServerListener implements ServletContextListener {
	private ServletContext context = null;
	private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
	private ResourceBundle resourceBoudle = null;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("Server started!");

		Config.getInsatnce();



		if (Config.use_redis==1){
			RedisPool.getInsatnce();
		}

		if (Config.use_mysql==1) {
			DB db = new DB();
		}else{
			SystemManager.putCode(1,1,"");
		}



	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		context = event.getServletContext();

		if (Config.use_mysql==1) {

			DB.quit();
			log.info("DB POOL closed!");
		}

		log.info("Server closed!");
	}
}