package com.migia.chatter.model;

public class User {
    private String username;
    private String phoneNo;
    private String email;


    public User(String username, String data, boolean isPhone) {
        this.username = username;
        if (isPhone)
            this.phoneNo = data;
        else
            this.email = data;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
