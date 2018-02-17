package com.nihatalim.messenger.helpers;

import android.app.Application;
import android.content.Intent;

import com.nihatalim.messenger.business.Facade;
import com.nihatalim.messenger.dto.User;
import com.nihatalim.messenger.services.SocketService;

/**
 * Created by thecower on 2/13/18.
 */

public class App extends Application{
    public static User user = null;

    public static SocketService service = null;
    public static Facade facade = null;

    @Override
    public void onCreate() {
        super.onCreate();
        App.facade = new Facade();
        startService(new Intent(this, SocketService.class));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService(new Intent(this, SocketService.class));
    }

}