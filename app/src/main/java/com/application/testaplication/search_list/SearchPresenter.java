package com.application.testaplication.search_list;

import androidx.annotation.NonNull;

import com.application.testaplication.pojo.Message;

import java.util.List;

public class SearchPresenter implements SearchContracts.Presenter,SearchContracts.Model.CallbackInterface{

    private SearchContracts.View searchActivity;
    private SearchContracts.Model searchModel;

    public SearchPresenter(SearchContracts.View view) {
        searchActivity = view;
        searchModel = new SearchModel();
    }

    @Override
    public void requestDataFromServer(String str) {
        if (searchActivity != null) {
            searchActivity.showProgress();
        }
        assert searchActivity != null;
        searchModel.getDateMessages(this,str);
    }

    @Override
    public void onSuccess(@NonNull List<Message> list) {
        searchActivity.setDataToRecyclerView(list);
        if (searchActivity != null) {
            searchActivity.hideProgress();
        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        throwable.printStackTrace();
        System.out.println("nothing");
    }
}
