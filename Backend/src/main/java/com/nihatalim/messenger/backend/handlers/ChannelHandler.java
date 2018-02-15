package com.nihatalim.messenger.backend.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChannelHandler extends TextWebSocketHandler {

    @Autowired
    private RequestHandler requestHandler;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Message incomed.");
        this.requestHandler.handle(session,message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //this.sessionHandler.addSession(session.getId(), session);
        System.out.println("SESSION COMED!");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //this.requestHandler.handle(session, new TextMessage(new Gson().toJson(new LogoutRequest())));
        this.requestHandler.logout(session,status);
        System.out.println("SESSION EXIT!");
    }
}