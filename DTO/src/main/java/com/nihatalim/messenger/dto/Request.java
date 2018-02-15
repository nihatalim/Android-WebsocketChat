package com.nihatalim.messenger.dto;

public class Request {
    private RequestType mRequestType;
    private Object mRequest;

    public Request() {
    }

    public Request(RequestType mRequestType, Object mRequest) {
        this.mRequestType = mRequestType;
        this.mRequest = mRequest;
    }

    public RequestType getmRequestType() {
        return mRequestType;
    }

    public void setmRequestType(RequestType mRequestType) {
        this.mRequestType = mRequestType;
    }

    public Object getmRequest() {
        return mRequest;
    }

    public void setmRequest(Object mRequest) {
        this.mRequest = mRequest;
    }

    public enum RequestType{
        LoginRequest("LoginRequest"),
        MessageRequest("MessageRequest");

        private String type;

        RequestType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}