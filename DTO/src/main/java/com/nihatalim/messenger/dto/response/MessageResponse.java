package com.nihatalim.messenger.dto.response;

import com.nihatalim.messenger.dto.Message;
import com.nihatalim.messenger.dto.User;

public class MessageResponse {
    private User owner;
    private Message message;

    public MessageResponse() {
    }

    public MessageResponse(User owner, Message message) {
        this.owner = owner;
        this.message = message;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}