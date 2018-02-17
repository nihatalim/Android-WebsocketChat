package com.nihatalim.messenger.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.nihatalim.messenger.R;
import com.nihatalim.messenger.business.interfaces.Connect;
import com.nihatalim.messenger.helpers.App;
import com.nihatalim.messenger.helpers.SocketListener;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

/**
 * Created by thecower on 2/13/18.
 */

public class SocketService extends Service{
    private WebSocket socket = null;
    private Map<String,Connect> invoker = null;
    private SocketListener listener = null;

    private Request request = null;
    private OkHttpClient client = null;

    private String URL = null;

    @Override
    public void onCreate() {
        super.onCreate();
        this.URL = getString(R.string.endpoint_url);
        this.client = new OkHttpClient();
        this.invoker = new HashMap<>();

        App.service = this;
        this.connect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        socket.close(Integer.parseInt(getString(R.string.closestatus_normal)), getString(R.string.closereason_serviceshutdown));
    }

    public void connect(){
        if(request == null) request = new Request.Builder().url(this.URL).build();
        if(listener == null) listener = new SocketListener();
        if(socket != null) socket.close(Integer.parseInt(getString(R.string.closestatus_normal)), getString(R.string.closereason_new));
        socket = client.newWebSocket(request, listener);
    }

    public WebSocket getSocket() {
        return socket;
    }

    public void setSocket(WebSocket socket) {
        this.socket = socket;
    }

    public Map<String, Connect> getInvoker() {
        return invoker;
    }

    public void setInvoker(Map<String, Connect> invoker) {
        this.invoker = invoker;
    }

    public SocketListener getListener() {
        return listener;
    }

    public void setListener(SocketListener listener) {
        this.listener = listener;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}