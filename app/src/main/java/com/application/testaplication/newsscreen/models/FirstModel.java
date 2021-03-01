package com.application.testaplication.newsscreen.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.application.testaplication.dao.MessagesDao;
import com.application.testaplication.database.AppDatabase;
import com.application.testaplication.di.component.DaggerNetworkComponent;
import com.application.testaplication.di.component.DaggerRoomDataComponent;
import com.application.testaplication.di.component.NetworkComponent;
import com.application.testaplication.di.component.RoomDataComponent;
import com.application.testaplication.di.modules.ContextModule;
import com.application.testaplication.di.modules.NetworkModule;
import com.application.testaplication.entities.MessageEntity;
import com.application.testaplication.newsscreen.FirstContract;
import com.application.testaplication.newsscreen.presenter.FirstPresenter;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.pojo.News;
import com.application.testaplication.network.NetworkServiceMessages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class FirstModel implements FirstContract.Model {

    private String TAG = "FIRST_MODEL";

    private List<Message> allMessages = new ArrayList<>();

    @Inject
    NetworkServiceMessages networkServiceMessages;
    @Inject
    AppDatabase database;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String API_KEY = "bc453cc87f8348e186ee35a0fe60d50f";
    private String SORTED_BY = "publishedAt";

    @Override
    public void getDateMessages(@Nullable final FirstPresenter presenter) {
       NetworkComponent networkComponent = DaggerNetworkComponent.builder()
               .networkModule(new NetworkModule())
               .build();

       networkServiceMessages = networkComponent.getNetworkService();

        allMessages.clear();
        networkServiceMessages.getJSONApi()
                .getMessageWithIDRX("world", simpleDateFormat.format(new Date()), SORTED_BY, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<News>() {
                    @Override
                    public void onNext(News news) {
                        allMessages.addAll(news.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        assert presenter != null;
                        presenter.setNews(allMessages);
                    }
                });

    }

    @Override
    public void getDateMessages(@Nullable final FirstPresenter presenter,String s) {
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();

        networkServiceMessages = networkComponent.getNetworkService();

        allMessages.clear();

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

        networkServiceMessages.getJSONApi()
                .getMessageWithCountryRX(s,API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<News>() {
                    @Override
                    public void onNext(News news) {
                        allMessages.addAll(news.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        assert presenter != null;
                        presenter.setNews(allMessages);
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

        }
    }

