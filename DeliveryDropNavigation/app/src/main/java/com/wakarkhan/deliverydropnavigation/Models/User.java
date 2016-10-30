package com.wakarkhan.deliverydropnavigation.Models;

/**
 * Created by HP on 27-10-2016.
 */
public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String uniqueId;
    private String password;
    private String oldPassword;
    private String newPassword;
    private String code;
    private Orders orders;
    private String user_id;
    private String new_addr1;
    private String new_city;
    private String scheduled_time;

    public String getUser_id() {
        return user_id;
    }

    public String getNew_addr1() {
        return new_addr1;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getScheduled_time() {
        return scheduled_time;
    }

    public String getNew_city() {
        return new_city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setNew_addr1(String new_addr1) {
        this.new_addr1 = new_addr1;
    }

    public void setNew_city(String new_city) {
        this.new_city = new_city;
    }

    public void setScheduled_time(String scheduled_time) {
        this.scheduled_time = scheduled_time;
    }
}
