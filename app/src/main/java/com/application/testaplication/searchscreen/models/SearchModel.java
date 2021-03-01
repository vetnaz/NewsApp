package com.application.testaplication.searchscreen.models;

import android.annotation.SuppressLint;

import com.application.testaplication.di.component.DaggerNetworkComponent;
import com.application.testaplication.di.component.NetworkComponent;
import com.application.testaplication.di.modules.NetworkModule;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.pojo.News;
import com.application.testaplication.network.NetworkServiceMessages;
import com.application.testaplication.searchscreen.SearchContracts;
import com.application.testaplication.searchscreen.presenter.SearchPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class SearchModel implements SearchContracts.Model {
    @Inject
    NetworkServiceMessages networkServiceMessages;

    private List<Message> allMessages;

    private String API_KEY = "bc453cc87f8348e186ee35a0fe60d50f";
    private String SORTED_BY = "publishedAt";


    @SuppressLint("CheckResult")
    @Override
    public void getDateMessages(SearchPresenter searchPresenter, String str) {
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .build();

        networkServiceMessages = networkComponent.getNetworkService();

        allMessages = new ArrayList<>();

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        networkServiceMessages.getJSONApi()
                .getMessageWithIDRX(str, simpleDateFormat.format(new Date()), SORTED_BY, API_KEY)
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
                        searchPresenter.setNews(allMessages);
                    }
                });

        }

}
