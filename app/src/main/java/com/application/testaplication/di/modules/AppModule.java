package com.application.testaplication.di.modules;

import android.app.Application;

import com.application.testaplication.MyApplication;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApplication app;

    public AppModule(MyApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return app;
    }
}
