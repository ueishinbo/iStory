package com.example.iStory.Object;

import java.util.List;

public class User {
    private String user_id;
    private String username;
    private String password;
    private String signature;
    private String tag;


    public User() {
    }

    public User(String userId, String userName, String password, String signature, String tag) {
        this.user_id = userId;
        this.username = userName;
        this.password = password;
        this.signature = signature;
        this.tag = tag;
    }

    public String getUserId() {
        return user_id;
    }


    public String getPassword() {
        return password;
    }

    public String getSignature() {
        return signature;
    }

    public String getTag() {
        return tag;
    }

    public String getUserName() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
