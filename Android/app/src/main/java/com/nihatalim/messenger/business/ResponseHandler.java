package com.nihatalim.messenger.business;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nihatalim.messenger.dto.Response;
import com.nihatalim.messenger.dto.response.LoginResponse;
import com.nihatalim.messenger.dto.response.MessageResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.WebSocket;

/**
 * Created by thecower on 2/13/18.
 */

public class ResponseHandler {

    private Facade facade = null;

    public ResponseHandler() {
        this.facade = new Facade();
    }

    public void handle(WebSocket webSocket, String text) {
        JSONObject jsonObject = null;
        JSONObject jsonResponse = null;
        Response.ResponseType type = null;

        try {
            jsonObject = new JSONObject(text);
            if (!jsonObject.isNull("mResponseType") && !jsonObject.isNull("mResponse")) {
                type = Response.ResponseType.valueOf(jsonObject.get("mResponseType").toString());
                jsonResponse = jsonObject.getJSONObject("mResponse");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        assert type != null;
        switch (type) {
            case LoginResponse:
                LoginResponse lp = new Gson().fromJson(jsonResponse.toString(), LoginResponse.class);
                this.facade.login(lp, webSocket);
                break;

            case MessageResponse:
                MessageResponse messageResponse = new GsonBuilder().setDateFormat("HH:mm:ss dd/MM/yyyy").create().fromJson(jsonResponse.toString(), MessageResponse.class);
                this.facade.message(messageResponse, webSocket);
                break;

        }
    }
}
