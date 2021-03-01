package com.application.testaplication.newsscreen.presenter;

import android.content.Context;

import com.application.testaplication.newsscreen.FirstContract;
import com.application.testaplication.pojo.Message;

import java.util.List;

import javax.inject.Inject;

public class FirstPresenter implements FirstContract.Presenter {

    private FirstContract.Model model;
    private FirstContract.View view;

    @Inject
    public FirstPresenter(FirstContract.View view, FirstContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestDataFromServer() {
        if (view != null) {
            view.showProgress();
        }
        assert view != null;
        model.getDateMessages(this);
    }

    @Override
    public void requestDataFromServer(String s) {
        if (view != null) {
            view.showProgress();
        }
        assert view != null;
        model.getDateMessages(this,s);
    }

    @Override
    public void putIntoDb(Message message, Context context) {
        model.setData(message,context);
    }

    @Override
    public void setNews(List<Message> messages){
        view.setDataToRecyclerView(messages);
        if (view != null) {
            view.hideProgress();
        }
    }
}
