package com.application.testaplication;

import android.app.Application;

import com.application.testaplication.di.component.DaggerNetworkComponent;
import com.application.testaplication.di.component.NetworkComponent;
import com.application.testaplication.di.modules.NetworkModule;


public class App extends Application {
    private static NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
         networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public static NetworkComponent networkServiceMessages() {
        return networkComponent;
    }


}
