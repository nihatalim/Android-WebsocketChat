package com.nihatalim.messenger.backend.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;

@Component
public class SessionHandler {
    private static HashMap<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private RoomHandler roomHandler;

    public void addSession(String id, WebSocketSession session){
        sessions.put(session.getId(), session);
    }

    public void removeSession(WebSocketSession session){
        sessions.remove(session.getId());
    }

    public void broadcast(WebSocketMessage message){
        sessions.forEach((id, session) -> {
            this.sendMessage(session,message);
        });
    }

    public void sendMessage(WebSocketSession session, WebSocketMessage message){
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createRoom(WebSocketSession session){
        this.roomHandler.addRoom(new Room(session.getId()));
    }

    public void removeRoom(Room room){
        this.roomHandler.removeRoom(room);
    }

    public void addSessionToRoom(Room room, WebSocketSession session){
        this.roomHandler.addSessionToRoom(room, session);
    }

    public void removeSessionFromRoom(Room room, WebSocketSession session){
        this.roomHandler.removeSessionFromRoom(room, session);
    }

    public void broadcastToRoom(Room room, WebSocketMessage message){
        this.roomHandler.broadcast(room, message);
    }

    public HashMap<String, WebSocketSession> getSessions() {
        return sessions;
    }
}