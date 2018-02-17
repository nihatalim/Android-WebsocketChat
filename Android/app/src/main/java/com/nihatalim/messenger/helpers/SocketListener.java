package com.nihatalim.messenger.helpers;

import android.util.Log;

import com.nihatalim.messenger.business.ResponseHandler;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by thecower on 2/13/18.
 */

public class SocketListener extends WebSocketListener{
    private static String TAG = "SocketListener";

    private ResponseHandler responseHandler = null;

    public SocketListener() {
        this.responseHandler = new ResponseHandler();
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        Log.i(TAG, "WebSocket opened.");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        Log.i(TAG, "WebSocket message received.");
        this.responseHandler.handle(webSocket,text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Log.i(TAG, "WebSocket closed. Code: " + Integer.toString(code) + " Reason: " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.e(TAG, "WebSocket failure. Failure message: " + t.getMessage());
        try {
            // Wait 1 sec
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        App.service.connect();
    }


}
