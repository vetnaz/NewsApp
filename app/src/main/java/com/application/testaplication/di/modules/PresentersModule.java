package com.application.testaplication.di.modules;

import com.application.testaplication.newsscreen.FirstContract;
import com.application.testaplication.newsscreen.presenter.FirstPresenter;
import com.application.testaplication.revisedscreen.RevisedContract;
import com.application.testaplication.revisedscreen.presenter.RevisedPresenter;
import com.application.testaplication.searchscreen.SearchContracts;
import com.application.testaplication.searchscreen.presenter.SearchPresenter;


import dagger.Module;
import dagger.Provides;

@Module(includes = ModelsModule.class)
public class PresentersModule {
    private FirstContract.View first;
    private RevisedContract.View revised;
    private SearchContracts.View search;

    public PresentersModule(FirstContract.View first) {
        this.first = first;
    }

    @Provides
    FirstContract.View getFirst() {
        return first;
    }


    @Provides
    FirstContract.Presenter provideFirstPresenter(FirstContract.View view, FirstContract.Model model) {
        return new FirstPresenter(view,model);
    }


    public PresentersModule(RevisedContract.View revised) {
        this.revised = revised;
    }

    @Provides
    RevisedContract.View getRevised() {
       return revised;
    }


    @Provides
    RevisedContract.Presenter provideRevisedPresenter(RevisedContract.View view, RevisedContract.Model model) {
        return new RevisedPresenter(view,model);
    }

    public PresentersModule(SearchContracts.View search) {
        this.search = search;
    }


    @Provides
    SearchContracts.View getSearch() {
        return search;
    }

    @Provides
    SearchContracts.Presenter provideSearchPresenter(SearchContracts.View view, SearchContracts.Model model) {
        return new SearchPresenter(view,model);
    }
}
