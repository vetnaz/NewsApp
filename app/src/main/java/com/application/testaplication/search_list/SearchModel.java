package com.application.testaplication.search_list;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.application.testaplication.App;
import com.application.testaplication.news_list.FirstPresenter;
import com.application.testaplication.pojo.News;
import com.application.testaplication.retrofit.NetworkServiceMessages;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchModel implements SearchContracts.Model {
    @Inject
    NetworkServiceMessages networkServiceMessages;

    @Override
    public void getDateMessages(@Nullable final SearchPresenter presenter,String str) {
        networkServiceMessages = App.networkServiceMessages().getNetworkService();

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        networkServiceMessages.getJSONApi()
                .getMessageWithID(str, simpleDateFormat.format(new Date()), "publishedAt", "a6362d35cfcd45ca92778e64f1e8e0e7")
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        if (response.isSuccessful()) {
                            News news = response.body();
                            assert news != null;
                            if (presenter != null) {
                                presenter.onSuccess(news.getArticles());
                            } else {
                                System.out.println(response.errorBody());
                            }
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
}
