package com.application.testaplication.searchscreen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.application.testaplication.MyApplication;
import com.application.testaplication.R;
import com.application.testaplication.di.component.DaggerPresenterComponent;
import com.application.testaplication.di.modules.PresentersModule;
import com.application.testaplication.newsdetailsscreen.NewsDetailsActivity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.recyclerview.RecyclerNewsAdapter;
import com.application.testaplication.searchscreen.SearchContracts;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContracts.View {

     private List<Message> messages;

     @Inject
     SearchContracts.Presenter searchPresenter;

     RecyclerView recyclerView;
     private RecyclerNewsAdapter adapter;
     private TextView textView;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initUi();

         DaggerPresenterComponent.builder()
                 .appComponent(MyApplication.getComponent())
                 .presentersModule(new PresentersModule(this))
                 .build()
                 .inject(this);
    }

     @Override
     public void initUi() {
         messages = new ArrayList<>();

         recyclerView = findViewById(R.id.searchRecycler);
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         adapter = new RecyclerNewsAdapter(messages,this);

         adapter.notifyDataSetChanged();
         recyclerView.setAdapter(adapter);

         textView = findViewById(R.id.noRezult);
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
                 if(newText.equals("")){
                     messages.clear();
                     adapter.notifyDataSetChanged();
                     if (messages.size()==0){
                         textView.setVisibility(View.VISIBLE);
                     }else {
                         textView.setVisibility(View.GONE);
                     }
                 }else {
                     searchPresenter.requestDataFromServer(newText);
                     adapter.notifyDataSetChanged();
                 }

                 return  true;
             }
         });
         return super.onCreateOptionsMenu(menu);
     }



     @Override
     public void setDataToRecyclerView(List<Message> list) {
         if (list.size()==0){
             textView.setVisibility(View.VISIBLE);
         }else {
             textView.setVisibility(View.GONE);
         }
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
