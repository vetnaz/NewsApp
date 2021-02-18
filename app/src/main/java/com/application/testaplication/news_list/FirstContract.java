package com.application.testaplication.news_list;

import android.content.Context;

import androidx.annotation.NonNull;

import com.application.testaplication.pojo.Message;
import com.application.testaplication.news_list.FirstPresenter;

import java.util.List;

public interface FirstContract {
    interface  View{
        void initUI();

        void showProgress();

        void setDataToRecyclerView(List<Message> list);

        void hideProgress();

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter{
        void requestDataFromServer();
        void requestDataFromServer(String s);
        void onDestroy();

        void putIntoDb(Message message, Context context);
    }

    interface Model{
        void getDateMessages(FirstPresenter presenter);
        void getDateMessages(FirstPresenter presenter,String s);

        void setData(Message message, Context context);

        interface CallbackInterface {
            void onSuccess(@NonNull List<Message> list);
            void onError(@NonNull Throwable throwable);
        }
    }
}
