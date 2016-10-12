package com.wakarkhan.deliverydropbeta.models;

/**
 * Created by HP on 10-09-2016.
 */
public class ServerResponse {

    private String result;
    private String message;
    private User user;

    public String getResult(){
        return result;
    }

    public String getMessage(){
        return message;
    }

    public User getUser(){
        return user;
    }
}
