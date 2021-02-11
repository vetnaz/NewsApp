package com.application.testaplication.news_list;

import androidx.annotation.NonNull;

import com.application.testaplication.pojo.Message;

import java.util.List;

public class FirstPresenter implements FirstContract.Presenter,FirstContract.Model.CallbackInterface {

    private FirstContract.Model model;
    private FirstContract.View view;

    public FirstPresenter(FirstContract.View view) {
        this.view = view;
        model = new FirstModel();
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
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccess(@NonNull List<Message> list) {
        view.setDataToRecyclerView(list);
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        view.onResponseFailure(throwable);
        if (view != null) {
            view.hideProgress();
        }
    }
}
