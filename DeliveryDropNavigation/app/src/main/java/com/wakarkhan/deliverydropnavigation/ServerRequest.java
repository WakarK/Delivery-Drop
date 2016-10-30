package com.wakarkhan.deliverydropnavigation;

import com.wakarkhan.deliverydropnavigation.Models.User;
/**
 * Created by HP on 27-10-2016.
 */
public class ServerRequest {
    private String operation;
    private User user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
