package com.nihatalim.messenger.backend.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class RoomHandler {

    private static Set<Room> rooms = new HashSet<>();

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void removeRoom(Room room){
        rooms.remove(room);
    }

    public void addSessionToRoom(Room room, WebSocketSession session){
        room.getSessions().add(session);
    }

    public void removeSessionFromRoom(Room room, WebSocketSession session){
        room.getSessions().remove(session);
    }

    public void broadcast(Room room, WebSocketMessage message){
        for (WebSocketSession session:room.getSessions()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String createRoomID(){
        return "room_" + Integer.toString (rooms.size() + 1);
    }
}
