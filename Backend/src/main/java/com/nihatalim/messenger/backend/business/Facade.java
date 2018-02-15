package com.nihatalim.messenger.backend.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nihatalim.messenger.backend.helpers.SessionHandler;
import com.nihatalim.messenger.dto.Response;
import com.nihatalim.messenger.dto.User;
import com.nihatalim.messenger.dto.request.LoginRequest;
import com.nihatalim.messenger.dto.request.MessageRequest;
import com.nihatalim.messenger.dto.response.LoginResponse;
import com.nihatalim.messenger.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.HashSet;
import java.util.Set;

@Component
public class Facade {

    private static Set<User> users;

    public Facade() {
        users = new HashSet<>();
    }

    @Autowired
    private SessionHandler sessionHandler;

    public void login(LoginRequest request, WebSocketSession session){
        // Creating new user for login request
        User user = new User(session.getId(), request.getName());

        // Adding user to set and creating a session in session handler
        users.add(user);
        this.sessionHandler.addSession(session.getId(),session);

        // Creating responses
        Response response = new Response();
        response.setmResponseType(Response.ResponseType.LoginResponse);

        response.setmResponse(new LoginResponse(true, "Session accepted", user));
        this.sessionHandler.sendMessage(session, new TextMessage(new Gson().toJson(response)));
    }

    public void message(MessageRequest request, WebSocketSession session){
        // First we need to find user from storaged users
        User owner = users.stream().filter(u -> u.getSessionID().equals(session.getId())).findFirst().orElse(null);
        if(owner != null){
            Response response = new Response();
            response.setmResponseType(Response.ResponseType.MessageResponse);
            response.setmResponse(new MessageResponse(owner, request.getMessage()));
            this.sessionHandler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("HH:mm:ss dd/MM/yyyy").create().toJson(response)));
        }
    }

    public void logout(WebSocketSession session, CloseStatus status){
        this.sessionHandler.removeSession(session);
        User user = users.stream().filter(u -> u.getSessionID().equals(session.getId())).findAny().orElse(null);
        users.remove(user);
    }
}
