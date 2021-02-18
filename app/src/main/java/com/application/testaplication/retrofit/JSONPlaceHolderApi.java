package com.application.testaplication.retrofit;

import com.application.testaplication.pojo.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("/v2/everything")
     Call<News> getMessageWithID(@Query("q")String q, @Query("from")String from, @Query("sortBy")String sortBy, @Query("apiKey")String apikey);

    @GET("/v2/top-headlines")
     Call<News> getMessageWithCountry(@Query("country")String country, @Query("apiKey")String apikey);

}
