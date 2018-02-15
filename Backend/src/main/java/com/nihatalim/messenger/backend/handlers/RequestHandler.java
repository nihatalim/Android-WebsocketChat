package com.nihatalim.messenger.backend.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nihatalim.messenger.backend.business.Facade;
import com.nihatalim.messenger.dto.Request.RequestType;
import com.nihatalim.messenger.dto.request.LoginRequest;
import com.nihatalim.messenger.dto.request.MessageRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class RequestHandler {
    @Autowired
    private Facade facade;

    public void handle(WebSocketSession session, TextMessage message) {
        RequestType type = null;
        JSONObject request = null;
        JSONObject jsonObject = new JSONObject(message.getPayload());

        // if the request has non values data
        if (!jsonObject.isNull("mRequestType") && !jsonObject.isNull("mRequest")) {
            try {
                type = jsonObject.getEnum(RequestType.class, "mRequestType");
                request = jsonObject.getJSONObject("mRequest");
            } catch (Exception e) {
                return;
            }

            switch (type) {
                case LoginRequest:
                    LoginRequest loginRequest = new Gson().fromJson(request.toString(), LoginRequest.class);
                    this.facade.login(loginRequest, session);
                    break;
                case MessageRequest:
                    MessageRequest messageRequest = new GsonBuilder().setDateFormat("HH:mm:ss dd/MM/yyyy").create().fromJson(request.toString(), MessageRequest.class);
                    this.facade.message(messageRequest, session);
                    break;
            }
        }
    }

    public void logout(WebSocketSession session, CloseStatus status){
        this.facade.logout(session,status);
    }
}