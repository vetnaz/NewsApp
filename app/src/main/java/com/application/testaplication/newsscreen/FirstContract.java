package com.application.testaplication.newsscreen;

import android.content.Context;

import com.application.testaplication.newsscreen.presenter.FirstPresenter;
import com.application.testaplication.pojo.Message;


import java.util.List;

public interface FirstContract {
    interface  View{
        void initUI();

        void showProgress();

        void setDataToRecyclerView(List<Message> list);

        void hideProgress();

    }

    interface Presenter{
        void requestDataFromServer();

        void requestDataFromServer(String s);

        void setNews(List<Message> messages);

        void putIntoDb(Message message, Context context);
    }

    interface Model{
        void getDateMessages(FirstPresenter presenter);

        void getDateMessages(FirstPresenter presenter,String s);

        void setData(Message message, Context context);
    }
}
