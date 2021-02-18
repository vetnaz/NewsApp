package com.application.testaplication.di.component;

import com.application.testaplication.database.AppDatabase;
import com.application.testaplication.di.modules.RoomDataModule;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = RoomDataModule.class)
public interface RoomDataComponent {
    AppDatabase getAppDatabase();
}
