package com.application.testaplication.searchscreen.presenter;

import com.application.testaplication.pojo.Message;
import com.application.testaplication.searchscreen.SearchContracts;

import java.util.List;

public class SearchPresenter implements SearchContracts.Presenter {

    private SearchContracts.View searchActivity;
    private SearchContracts.Model searchModel;

    public SearchPresenter(SearchContracts.View view, SearchContracts.Model model) {
        searchActivity = view;
        searchModel = model;
    }

    @Override
    public void requestDataFromServer(String str) {
        assert searchActivity != null;
        searchModel.getDateMessages(this,str);
    }

    @Override
    public void setNews(List<Message> messages){
        searchActivity.setDataToRecyclerView(messages);
    }

}
