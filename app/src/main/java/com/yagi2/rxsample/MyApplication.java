package com.yagi2.rxsample;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        Timber.plant(new Timber.DebugTree());
    }
}
