package com.application.testaplication.search_list;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.testaplication.news_list.FirstPresenter;
import com.application.testaplication.pojo.Message;

import java.util.List;

public interface SearchContracts {
    interface  View{

        void showProgress();

        void hideProgress();

        void onNewsClickListener(int position);

        void setDataToRecyclerView(List<Message> list);
    }

    interface Presenter{
         void requestDataFromServer(String str);
    }

    interface Model {
        interface CallbackInterface {
            void onSuccess(@NonNull List<Message> list);
            void onError(@NonNull Throwable throwable);
        }
        public void getDateMessages(@Nullable final SearchPresenter presenter, String str);
    }
}
