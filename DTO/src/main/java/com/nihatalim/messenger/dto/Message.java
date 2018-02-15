package com.nihatalim.messenger.dto;

import java.util.Date;

public class Message {
    private User owner;
    private String message;
    private Date date;

    public Message() {
        this.date = new Date();
    }

    public Message(User owner, String message, Date date) {
        this();
        this.owner = owner;
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
