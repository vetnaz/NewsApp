package com.application.testaplication.searchscreen;


import com.application.testaplication.pojo.Message;
import com.application.testaplication.searchscreen.presenter.SearchPresenter;

import java.util.List;

public interface SearchContracts {
    interface  View{
        void initUi();

        void onNewsClickListener(int position);

        void setDataToRecyclerView(List<Message> list);
    }

    interface Presenter{
         void requestDataFromServer(String str);

         void setNews(List<Message> messages);
    }

    interface Model {
        void getDateMessages(SearchPresenter searchPresenter, String str);
    }
}
