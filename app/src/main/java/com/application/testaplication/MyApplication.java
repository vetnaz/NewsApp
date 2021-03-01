package com.application.testaplication;

import android.app.Application;

import com.application.testaplication.di.component.AppComponent;
import com.application.testaplication.di.component.DaggerAppComponent;


public class MyApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
    }

    public static AppComponent getComponent() {
        return component;
    }

}
