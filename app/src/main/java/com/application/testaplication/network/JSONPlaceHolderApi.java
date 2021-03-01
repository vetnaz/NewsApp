package com.application.testaplication.network;

import com.application.testaplication.pojo.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("/v2/everything")
    Observable<News> getMessageWithIDRX(@Query("q")String q, @Query("from")String from, @Query("sortBy")String sortBy, @Query("apiKey")String apikey);

    @GET("/v2/top-headlines")
    Observable<News> getMessageWithCountryRX(@Query("country")String country, @Query("apiKey")String apikey);

}
