package com.application.testaplication.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
    @SerializedName("status")
    @Expose
    private
    String status;
    @SerializedName("totalResults")
    @Expose
    private
    int totalResults;
    @SerializedName("articles")
    @Expose
    private
    List<Message> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Message> getArticles() {
        return articles;
    }

    public void setArticles(List<Message> articles) {
        this.articles = articles;
    }
}
