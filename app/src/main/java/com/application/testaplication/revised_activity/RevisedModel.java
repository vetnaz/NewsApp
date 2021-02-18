package com.application.testaplication.revised_activity;

import android.content.Context;

import com.application.testaplication.dao.MessagesDao;
import com.application.testaplication.database.AppDatabase;
import com.application.testaplication.di.component.DaggerRoomDataComponent;
import com.application.testaplication.di.component.RoomDataComponent;
import com.application.testaplication.di.modules.ContextModule;
import com.application.testaplication.entities.MessageEntity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.pojo.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RevisedModel implements RevisedContract.Model {

    @Inject
    AppDatabase database;
    private List<Message> messages;


    @Override
    public List<Message> getData(Context context) {
        RoomDataComponent roomDataComponent = DaggerRoomDataComponent.builder()
                .contextModule(new ContextModule(context))
                .build();
        messages = new ArrayList<>();

        database = roomDataComponent.getAppDatabase();

        MessagesDao messagesDao = database.employeeDao();

        for (MessageEntity messageEntity:messagesDao.getAll()) {
            Message message = new Message();
            Source source = new Source();

            source.setName(messageEntity.source);

            message.setAuthor(messageEntity.author);
            message.setTitle(messageEntity.title);
            message.setContent(messageEntity.content);
            message.setSource(source);
            message.setUrl(messageEntity.url);
            message.setUrlToImage(messageEntity.urlToImage);
            message.setDescription(messageEntity.description);
            message.setPublishedAt(messageEntity.publishedAt);

            messages.add(message);
        }

        return messages;
    }
}
