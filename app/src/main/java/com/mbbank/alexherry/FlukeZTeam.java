package com.mbbank.alexherry;

import android.app.Application;
import android.content.Context;
//import android.content.Intent;
//import android.os.Process;
//import android.util.Log;

public class FlukeZTeam extends Application {

    private static Context mApplicationContext;

    public static Context getContext() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        mApplicationContext = getApplicationContext();
        super.onCreate();
    }
}