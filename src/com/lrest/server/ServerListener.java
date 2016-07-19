package com.lrest.server;


import com.lrest.server.services.Config;
import com.lrest.server.services.DB;
import com.lrest.server.services.SystemManager;
//import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@WebListener
public class ServerListener implements ServletContextListener {
	private ServletContext context = null;
	private   final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
	private ResourceBundle resourceBoudle = null;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("Server started!");

		try {
			resourceBoudle = new PropertyResourceBundle(
					getClass()
							.getClassLoader()
							.getResourceAsStream(
									"config/config.properties"));

			//log.debug("resouce mysql="+resourceBoudle.getString("mysql"));

			Config.session_timeout=Integer.parseInt(resourceBoudle.getString("session_timeout"));

			Config.use_mysql=Integer.parseInt(resourceBoudle.getString("use_mysql"));
			Config.mysql_jdbcurl=resourceBoudle.getString("mysql_jdbcurl");
			Config.mysql_user=resourceBoudle.getString("mysql_user");
			Config.mysql_password=resourceBoudle.getString("mysql_password");

			Config.use_redis=Integer.parseInt(resourceBoudle.getString("use_redis"));
			Config.redis_ip=resourceBoudle.getString("redis_ip");
			Config.redis_port=Integer.parseInt(resourceBoudle.getString("redis_port"));

		} catch (Exception e) {
			e.printStackTrace();
			// LOGGER.error("Error loading messages properties", ex);
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

		log.info("Server closed!");
	}
}