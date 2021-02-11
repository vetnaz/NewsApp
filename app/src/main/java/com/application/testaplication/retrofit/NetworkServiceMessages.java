package com.application.testaplication.retrofit;

import retrofit2.Retrofit;

public class NetworkServiceMessages{

    private Retrofit mRetrofit;

    public NetworkServiceMessages(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}
