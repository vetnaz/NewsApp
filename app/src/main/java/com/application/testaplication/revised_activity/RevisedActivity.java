package com.application.testaplication.revised_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.testaplication.R;
import com.application.testaplication.news_details.NewsDetailsActivity;
import com.application.testaplication.news_list.FirstContract;
import com.application.testaplication.news_list.FirstPresenter;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.recyclerView.RecyclerNewsAdapter;
import com.application.testaplication.search_list.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RevisedActivity extends AppCompatActivity implements RevisedContract.View {

    private static final String TAG = "REVISED_ACTIVITY";
    private RevisedContract.Presenter presenter;
    private List<Message> messages;
    private ProgressBar pbLoading;

    private RecyclerView recyclerView;
    private RecyclerNewsAdapter adapter;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revised);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Revised");
        initUI();

        presenter = new RevisedPresenter(this);

        presenter.requestDataFromDatabase();
    }

    @Override
    public void initUI() {
        messages = new ArrayList<>();

        pbLoading =findViewById(R.id.revisedPbLoading);

        recyclerView = findViewById(R.id.revisedRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerNewsAdapter(messages,this);

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDataToRecyclerView(List<Message> list) {
        messages.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }


    @Override
    public void onNewsClickListener(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this, NewsDetailsActivity.class);
        detailIntent.putExtra("title",messages.get(position).getTitle());
        detailIntent.putExtra("author",messages.get(position).getAuthor());
        detailIntent.putExtra("content",messages.get(position).getContent());
        detailIntent.putExtra("publishedAt",messages.get(position).getPublishedAt());
        detailIntent.putExtra("source",messages.get(position).getSource().getName());
        detailIntent.putExtra("url",messages.get(position).getUrl());
        detailIntent.putExtra("urlToImage",messages.get(position).getUrlToImage());

        startActivity(detailIntent);
    }

}
