package com.nihatalim.messenger.dto.request;

/**
 * Created by thecower on 2/15/18.
 */

public class LoginRequest {
    private String name;

    public LoginRequest() {
    }

    public LoginRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}