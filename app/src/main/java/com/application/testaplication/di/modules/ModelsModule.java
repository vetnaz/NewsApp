package com.application.testaplication.di.modules;

import com.application.testaplication.newsscreen.FirstContract;
import com.application.testaplication.newsscreen.models.FirstModel;
import com.application.testaplication.revisedscreen.RevisedContract;
import com.application.testaplication.revisedscreen.models.RevisedModel;
import com.application.testaplication.searchscreen.SearchContracts;
import com.application.testaplication.searchscreen.models.SearchModel;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelsModule {

    @Provides
    FirstContract.Model provideModelClass() {
        return new FirstModel();
    }

    @Provides
    RevisedContract.Model providedRevisedModule() {
        return new RevisedModel();
    }

    @Provides
    SearchContracts.Model providedSearchModule() {
        return new SearchModel();
    }
}
