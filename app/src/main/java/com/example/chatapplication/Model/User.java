package com.example.chatapplication.Model;

public class User {
    private String username = "q";
    private String id = "s";
    private String imageUrl = "s";

    public User(String id, String username, String imageUrl){
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
    }
    public User(){
        username = "inclass";
        id = "inclass";
        imageUrl = "inclass";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
