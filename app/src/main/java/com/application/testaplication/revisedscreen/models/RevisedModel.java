package com.application.testaplication.revisedscreen.models;

import android.content.Context;

import com.application.testaplication.dao.MessagesDao;
import com.application.testaplication.database.AppDatabase;
import com.application.testaplication.di.component.DaggerRoomDataComponent;
import com.application.testaplication.di.component.RoomDataComponent;
import com.application.testaplication.di.modules.ContextModule;
import com.application.testaplication.entities.MessageEntity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.pojo.Source;
import com.application.testaplication.revisedscreen.RevisedContract;
import com.application.testaplication.revisedscreen.presenter.RevisedPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RevisedModel implements RevisedContract.Model {

    @Inject
    AppDatabase database;

    private List<Message> messages;

    @Override
    public void getData(Context context, RevisedPresenter revisedPresenter) {
        RoomDataComponent roomDataComponent = DaggerRoomDataComponent.builder()
                .contextModule(new ContextModule(context))
                .build();

        messages = new ArrayList<>();

        database = roomDataComponent.getAppDatabase();

        MessagesDao messagesDao = database.employeeDao();

        messagesDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<MessageEntity>>() {
                    @Override
                    public void onSuccess(List<MessageEntity> messageEntities) {
                        for (MessageEntity messageList:messageEntities) {
                            Message message = new Message();
                            Source source = new Source();
                            source.setName(messageList.source);
                            message.setAuthor(messageList.author);
                            message.setTitle(messageList.title);
                            message.setContent(messageList.content);
                            message.setSource(source);
                            message.setUrl(messageList.url);
                            message.setUrlToImage(messageList.urlToImage);
                            message.setDescription(messageList.description);
                            message.setPublishedAt(messageList.publishedAt);
                            messages.add(message);
                        }

                        revisedPresenter.setData(messages);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                });
    }


}
