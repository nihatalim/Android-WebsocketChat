package com.nihatalim.messenger.dto.response;

import com.nihatalim.messenger.dto.User;

/**
 * Created by thecower on 2/15/18.
 */

public class LoginResponse {
    private boolean response;
    private String message;
    private User user;


    public LoginResponse() {
    }

    public LoginResponse(boolean response, String message, User user) {
        this.response = response;
        this.message = message;
        this.user = user;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
