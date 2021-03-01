package com.application.testaplication.revisedscreen.presenter;

import android.content.Context;

import com.application.testaplication.pojo.Message;
import com.application.testaplication.revisedscreen.RevisedContract;

import java.util.List;

public class RevisedPresenter implements RevisedContract.Presenter {

    private RevisedContract.Model model;
    private RevisedContract.View view;

    public RevisedPresenter(RevisedContract.View view,RevisedContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestDataFromDatabase() {
        model.getData((Context) view,this);
        view.hideProgress();
    }

    public void setData(List<Message> messages){
        view.setDataToRecyclerView(messages);
    }
}
