package com.lrest.server;

/**
 * Created by angrycans on 15/7/7.
 */

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lrest.server.modules.GuiceModule;
import com.lrest.server.providers.GsonJerseyProvider;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;
import java.util.Properties;

@WebServlet(loadOnStartup = 1)
@ApplicationPath("/api/*")

public class Application extends ResourceConfig {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    public Application(ServiceLocator serviceLocator) {

        packages("com.lrest.server");


        Properties props = System.getProperties();
        log.debug("Current working directory is " + props.getProperty("user.dir"));
        log.info("Current working directory is " + props.getProperty("user.dir"));
        //PropertyConfigurator.configure("log4j.properties");

        log.info("Registering injectables...");


        Injector injector = Guice.createInjector(new GuiceModule());

        register(RequestFilter.class);
        register(ResponseFilter.class);

        register(GsonJerseyProvider.class);

        register(ServerListener.class);


        //property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, false);
        //property(ServerProperties.JSON_PROCESSING_FEATURE_DISABLE, false);

        // Guice HK2 bridge
        // See e.g. https://github.com/t-tang/jetty-jersey-HK2-Guice-boilerplate
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge bridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        bridge.bridgeGuiceInjector(injector);





        //RedisSessionManager rsm=new RedisSessionManager();

        //init mysql
        //DB db=new DB();
        //db.testDb();


        //log.debug("'{}' initialized" , getClass().getName());

        log.debug("'{}' initialized" , getClass().getName());
    }
}