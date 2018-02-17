package com.example.o2.anayumeapp;

import android.app.Application;

public class ApplicationController extends Application {
    private static ApplicationController sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }
}