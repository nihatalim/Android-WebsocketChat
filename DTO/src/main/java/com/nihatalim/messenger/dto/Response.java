package com.nihatalim.messenger.dto;

public class Response{
    private ResponseType mResponseType;
    private Object mResponse;

    public Response() {
    }

    public Response(ResponseType mResponseType, Object mResponse) {
        this.mResponseType = mResponseType;
        this.mResponse = mResponse;
    }

    public ResponseType getmResponseType() {
        return mResponseType;
    }

    public void setmResponseType(ResponseType mResponseType) {
        this.mResponseType = mResponseType;
    }

    public Object getmResponse() {
        return mResponse;
    }

    public void setmResponse(Object mResponse) {
        this.mResponse = mResponse;
    }

    public enum ResponseType{
        LoginResponse("LoginResponse"),
        MessageResponse("MessageResponse");

        private String type;

        ResponseType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
