package com.application.testaplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.application.testaplication.dao.MessagesDao;
import com.application.testaplication.entities.MessageEntity;

@Database(entities = {MessageEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MessagesDao employeeDao();
}