package com.application.testaplication.search_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.application.testaplication.R;
import com.application.testaplication.news_details.NewsDetailsActivity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.recyclerView.RecyclerNewsAdapter;

import java.util.ArrayList;
import java.util.List;

 public class SearchActivity extends AppCompatActivity implements SearchContracts.View{

     List<Message> messages;
     SearchPresenter searchPresenter;
     RecyclerView recyclerView;
     RecyclerNewsAdapter adapter;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initUi();

        searchPresenter = new SearchPresenter(this);
    }

     private void initUi() {
         messages = new ArrayList<>();

         recyclerView = findViewById(R.id.searchRecycler);
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         adapter = new RecyclerNewsAdapter(messages,this);

         adapter.notifyDataSetChanged();
         recyclerView.setAdapter(adapter);
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.search_list_menu,menu);
         MenuItem menuItem = menu.findItem(R.id.search_icon);
         SearchView searchView = (SearchView) menuItem.getActionView();
         searchView.setQueryHint("Search here");

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String query) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String newText) {
                 searchPresenter.requestDataFromServer(newText);
                 adapter.notifyDataSetChanged();

                 return  true;
             }
         });
         return super.onCreateOptionsMenu(menu);
     }

     @Override
     public void showProgress() {

     }

     @Override
     public void hideProgress() {

     }

     @Override
     public void setDataToRecyclerView(List<Message> list) {
         messages.clear();
         messages.addAll(list);
         adapter.notifyDataSetChanged();
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
