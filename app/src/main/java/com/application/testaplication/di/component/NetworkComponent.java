package com.application.testaplication.di.component;

import com.application.testaplication.di.modules.NetworkModule;
import com.application.testaplication.news_list.FirstModel;
import com.application.testaplication.retrofit.NetworkServiceMessages;
import com.application.testaplication.search_list.SearchModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    NetworkServiceMessages getNetworkService();
}
