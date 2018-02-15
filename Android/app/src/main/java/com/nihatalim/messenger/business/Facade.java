package com.nihatalim.messenger.business;

import com.nihatalim.messenger.dto.Response;
import com.nihatalim.messenger.dto.response.LoginResponse;
import com.nihatalim.messenger.dto.response.MessageResponse;
import com.nihatalim.messenger.services.SocketService;

import okhttp3.WebSocket;

/**
 * Created by thecower on 2/13/18.
 */

public class Facade {

    public void login(LoginResponse loginResponse, WebSocket webSocket) {
        SocketService.iFace.get("LoginResponse").run(loginResponse);
    }

    public void message(MessageResponse messageResponse, WebSocket webSocket){
        SocketService.iFace.get("MessageResponse").run(messageResponse);
    }

}
