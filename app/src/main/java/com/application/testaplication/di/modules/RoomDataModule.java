package com.application.testaplication.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.application.testaplication.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class RoomDataModule {

    @Singleton
    @Provides
    AppDatabase appDatabase(Context context){
        return Room.databaseBuilder(context,
                AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }
}
