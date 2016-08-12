package com.lrest.server.modules;

/**
 * Created by angrycans on 15/7/7.
 */
import com.lrest.server.controller.BaseController;
import com.lrest.server.services.*;
import com.google.inject.Binder;
import com.google.inject.Module;

//public class HelloGuiceModule extends AbstractModule {
public class GuiceModule implements Module {
    @Override
    public void configure(Binder binder) {


        binder.bind(SystemManager.class);
        

        System.out.println("GuiceModule binder ok" );
    }
}
