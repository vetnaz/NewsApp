package com.application.testaplication.news_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.application.testaplication.App;
import com.application.testaplication.dao.MessagesDao;
import com.application.testaplication.database.AppDatabase;
import com.application.testaplication.di.component.DaggerRoomDataComponent;
import com.application.testaplication.di.component.RoomDataComponent;
import com.application.testaplication.di.modules.ContextModule;
import com.application.testaplication.entities.MessageEntity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.pojo.News;
import com.application.testaplication.retrofit.NetworkServiceMessages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstModel implements FirstContract.Model {

    String TAG = "FIRST_MODEL";
    @Inject
    NetworkServiceMessages networkServiceMessages;
    @Inject
    AppDatabase database;

    public void getDateMessages(@Nullable final FirstPresenter presenter) {
        networkServiceMessages = App.networkServiceMessages().getNetworkService();
        @SuppressLint("SimpleDateFormat")

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        networkServiceMessages.getJSONApi()
                .getMessageWithID("world", simpleDateFormat.format(new Date()), "publishedAt", "a6362d35cfcd45ca92778e64f1e8e0e7")
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()) {
                            News news = response.body();
                            assert news != null;
                            if (presenter != null)
                                presenter.onSuccess(news.getArticles());
                        } else {
                            System.out.println(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        if (presenter != null) {
                            presenter.onError(t);
                        }
                    }
                });
    }

    public void getDateMessages(@Nullable final FirstPresenter presenter,String s) {
        switch (s){
            case "USA":s="us";
                break;
            case "Ukraine":s="ua";
                break;
            case "Germany":s="de";
                break;
            case "Great Britain":s="gb";
                break;
            case "Russia":s="ru";
                break;
            case "France":s="fr";
                break;
            case "Italy":s="it";
        }

        networkServiceMessages = App.networkServiceMessages().getNetworkService();
        @SuppressLint("SimpleDateFormat")

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        networkServiceMessages.getJSONApi()
                .getMessageWithCountry(s,"a6362d35cfcd45ca92778e64f1e8e0e7")
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()) {
                            News news = response.body();
                            assert news != null;
                            if (presenter != null)
                                presenter.onSuccess(news.getArticles());
                        } else {
                            System.out.println(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        if (presenter != null) {
                            presenter.onError(t);
                        }
                    }
                });
    }


    @Override
    public void setData(Message message, Context context) {
            RoomDataComponent roomDataComponent = DaggerRoomDataComponent.builder()
                    .contextModule(new ContextModule(context))
                    .build();

            database = roomDataComponent.getAppDatabase();

            MessagesDao messagesDao = database.employeeDao();

            MessageEntity messageEntity = new MessageEntity();

            messageEntity.author=message.getAuthor();
            messageEntity.title=message.getTitle();
            messageEntity.content=message.getContent();
            messageEntity.description=message.getDescription();
            messageEntity.publishedAt=message.getPublishedAt();
            messageEntity.url=message.getUrl();
            messageEntity.urlToImage=message.getUrlToImage();
            messageEntity.source=message.getSource().getName();

            try {
                messagesDao.insert(messageEntity);
            }catch (Exception e){
                Log.d(TAG,"Repeats meaning");
            }


            List<MessageEntity> list = messagesDao.getAll();

            System.out.println(list.size());

        }
    }

