package com.application.testaplication.revised_activity;

import android.content.Context;

import com.application.testaplication.pojo.Message;

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

        List<Message> getData(Context context);
    }
}
