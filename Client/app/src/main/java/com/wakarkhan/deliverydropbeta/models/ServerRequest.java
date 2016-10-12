package com.wakarkhan.deliverydropbeta.models;

/**
 * Created by HP on 10-09-2016.
 */
public class ServerRequest {

    private String operation;
    private User user;

    public void setOperation(String operation){
        this.operation=operation;
    }

    public void setUser(User user){
        this.user=user;
    }
}
