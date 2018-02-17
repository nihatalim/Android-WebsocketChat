package com.nihatalim.messenger.business;

import android.content.Context;

import com.nihatalim.messenger.business.database.DbFacade;
import com.nihatalim.messenger.business.interfaces.Connect;
import com.nihatalim.messenger.dto.response.LoginResponse;
import com.nihatalim.messenger.dto.response.MessageResponse;
import com.nihatalim.messenger.helpers.App;

import okhttp3.WebSocket;

/**
 * Created by thecower on 2/13/18.
 */

public class Facade {
    private DbFacade dbFacade = null;

    public Facade() {}

    public Facade(Context context){
        this();
        this.dbFacade = new DbFacade(context);
    }

    public void login(LoginResponse loginResponse, WebSocket webSocket) {
        this.invoke("LoginResponse", loginResponse);
    }

    public void message(MessageResponse messageResponse, WebSocket webSocket){
        this.dbFacade.saveMessage(messageResponse.getMessage());
        this.invoke("MessageResponse", messageResponse);
    }

    private void invoke(String invokeID, Object object){
        Connect connect = App.service.getInvoker().get(invokeID);
        if(connect!=null){
            connect.run(object);
        }
    }

    public DbFacade getDbFacade() {
        return dbFacade;
    }

    public void setDbFacade(DbFacade dbFacade) {
        this.dbFacade = dbFacade;
    }
}
