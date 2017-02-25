package com.hujie.fazhiregister;

import android.app.Application;

/**
 * Created by hujie on 2017/2/23.
 */

public class App extends Application{
    private static App app;

    public static App getApp(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
