package com.nihatalim.messenger.dto;

public class User {
    private String sessionID;
    private String name;

    public User() {
    }

    public User(String sessionID, String name) {
        this.sessionID = sessionID;
        this.name = name;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
