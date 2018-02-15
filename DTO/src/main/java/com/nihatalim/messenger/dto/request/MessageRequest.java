package com.nihatalim.messenger.dto.request;

import com.nihatalim.messenger.dto.Message;

import java.util.Date;

public class MessageRequest {
    private Message message;

    public MessageRequest() {
    }

    public MessageRequest(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
