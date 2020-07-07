package com.example.chatapplication.Model;

public class User {
    private String username ="default";
    private String userid ="default";
    private String imageURL ="default";

    public User(String userid, String username, String imageURL){
        this.userid = userid;
        this.username = username;
        this.imageURL = imageURL;
    }
    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
