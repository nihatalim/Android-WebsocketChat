package com.nihatalim.messenger.backend.helpers;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private String roomID;
    private String hostID;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public Room() {
        this.roomID = RoomHandler.createRoomID();
    }

    public Room(String hostID) {
        this.hostID = hostID;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }

    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<WebSocketSession> sessions) {
        this.sessions = sessions;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
