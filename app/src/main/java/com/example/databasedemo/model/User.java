package com.example.databasedemo.model;

public class User {

    private String username;
    private String password;

    public User(String username, String passord) {
        this.username = username;
        this.password = passord;


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passord) {
        this.password = passord;
    }
}
