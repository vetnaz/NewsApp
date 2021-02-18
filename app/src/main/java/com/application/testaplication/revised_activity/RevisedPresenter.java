package com.application.testaplication.revised_activity;

import android.content.Context;

public class RevisedPresenter implements RevisedContract.Presenter {

    private RevisedContract.Model model;
    private RevisedContract.View view;

    RevisedPresenter(RevisedContract.View view) {
        this.view = view;
        model = new RevisedModel();
    }

    @Override
    public void requestDataFromDatabase() {
        view.setDataToRecyclerView( model.getData((Context) view));
        view.hideProgress();
    }
}
