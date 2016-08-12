package com.lrest.server.services;

/**
 * Created by acans on 16/6/23.
 */

public  interface SessionManagerInteface {
    public  String createSID(String _token, String _uid);
    public  String getSID(String _sid);
    public void delSID(String _sid);



}
