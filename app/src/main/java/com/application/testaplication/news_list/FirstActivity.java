package com.application.testaplication.news_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.application.testaplication.R;
import com.application.testaplication.news_details.NewsDetailsActivity;
import com.application.testaplication.news_list.dialog.FilterDialog;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.recyclerView.RecyclerNewsAdapter;
import com.application.testaplication.revised_activity.RevisedActivity;
import com.application.testaplication.search_list.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@SuppressLint("Registered")
public class FirstActivity extends AppCompatActivity implements FirstContract.View,NewsClickListener, View.OnClickListener {

    private static final String TAG = "FIRST_ACTIVITY";
    private static FirstContract.Presenter presenter;
    private static List<Message> messages;
    private ProgressBar pbLoading;
    private FloatingActionButton floatingActionButton;

    private RecyclerView recyclerView;
    private static RecyclerNewsAdapter adapter;
    private FragmentManager fragmentManager;
    private FilterDialog filterDialog;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.app_name));
        initUI();

        Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();

        presenter = new FirstPresenter(this);

        presenter.requestDataFromServer();
    }

    @Override
    public void initUI() {
        messages = new ArrayList<>();

        pbLoading =findViewById(R.id.pb_loading);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerNewsAdapter(messages,this);

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        filterDialog = new FilterDialog();
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
    public void onResponseFailure(Throwable throwable) {

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

        presenter.putIntoDb(messages.get(position),this);
        startActivity(detailIntent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        
        switch (item.getItemId()) {
            case  R.id.revised:{ intent = new Intent(this, RevisedActivity.class);
               startActivity(intent);
            }
            break;
            case  R.id.filter:filterDialog.show(fragmentManager,"Choose country"); ;
            break;
            default: throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        
        return super.onOptionsItemSelected(item);
    }

    public static void changeCountries(String country){
        presenter.requestDataFromServer(country);
        messages.clear();
        adapter.notifyDataSetChanged();
    }
}

