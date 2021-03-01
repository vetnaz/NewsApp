package com.application.testaplication.di.component;

import com.application.testaplication.di.modules.NetworkModule;
import com.application.testaplication.network.NetworkServiceMessages;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    NetworkServiceMessages getNetworkService();
}
