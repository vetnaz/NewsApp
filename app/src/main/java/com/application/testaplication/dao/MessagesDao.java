package com.application.testaplication.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.application.testaplication.entities.MessageEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MessagesDao {

    @Query("SELECT DISTINCT * FROM messageentity")
    Single<List<MessageEntity>> getAll();

    @Insert
    void insert(MessageEntity messageEntity);

    @Delete
    void delete(MessageEntity messageEntity);

    @Update
    void update(MessageEntity messageEntity);
}
