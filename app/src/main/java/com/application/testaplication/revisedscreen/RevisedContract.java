package com.application.testaplication.revisedscreen;

import android.content.Context;

import com.application.testaplication.pojo.Message;
import com.application.testaplication.revisedscreen.presenter.RevisedPresenter;

import java.util.List;

public interface RevisedContract {
    interface View{
        void initUI();
        void showProgress();
        void hideProgress();
        void onNewsClickListener(int position);
        void setDataToRecyclerView(List<Message> list);
    }

    interface Presenter{
        void requestDataFromDatabase();
    }

    interface Model{
        void getData(Context context, RevisedPresenter revisedPresenter);
    }
}
