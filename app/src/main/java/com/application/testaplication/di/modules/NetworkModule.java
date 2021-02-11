package com.application.testaplication.di.modules;

import com.application.testaplication.retrofit.NetworkServiceMessages;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    NetworkServiceMessages networkServiceMessages(Retrofit retrofit){
        return new NetworkServiceMessages(retrofit);
    }

    @Singleton
    @Provides
    Retrofit retrofit(GsonConverterFactory gsonConverterFactory){
         return new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Singleton
    @Provides
    GsonConverterFactory gsonConverterFactory(){
        return GsonConverterFactory.create();
    }
}
