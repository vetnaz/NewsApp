package com.application.testaplication.revisedscreen.view;

import androidx.annotation.RecentlyNonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.application.testaplication.MyApplication;
import com.application.testaplication.R;
import com.application.testaplication.di.component.DaggerPresenterComponent;
import com.application.testaplication.di.modules.PresentersModule;
import com.application.testaplication.newsdetailsscreen.NewsDetailsActivity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.recyclerview.RecyclerNewsAdapter;
import com.application.testaplication.revisedscreen.RevisedContract;
import com.application.testaplication.revisedscreen.dialog.RevisedFilterDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class RevisedActivity extends AppCompatActivity implements RevisedContract.View {

    private static final String TAG = "REVISED_ACTIVITY";

    @Inject
    RevisedContract.Presenter presenter;

    private static List<Message> messages;
    private ProgressBar pbLoading;
    private static boolean order = false;

    private RecyclerView recyclerView;
    private static RecyclerNewsAdapter adapter;


    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revised);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Revised");
        initUI();

        DaggerPresenterComponent.builder()
                .appComponent(MyApplication.getComponent())
                .presentersModule(new PresentersModule(this))
                .build()
                .inject(this);

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.revised_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RevisedFilterDialog revisedFilterDialog = new RevisedFilterDialog();
        FragmentManager fragmentManager = getSupportFragmentManager();

        revisedFilterDialog.show(fragmentManager,"Choose order");

        return super.onOptionsItemSelected(item);
    }

    public static void changeOrder(String s) {
        switch (s) {
            case "Old":
                if (order) {
                    Collections.reverse(messages);
                }
            case "New":
                if (!order) {
                    Collections.reverse(messages);
                }
                adapter.notifyDataSetChanged();
        }
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
