package com.application.testaplication.newsdetailsscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.testaplication.R;
import com.application.testaplication.browserscreen.BrowserActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

   private TextView date;
   private TextView content;
   private TextView title;
   private TextView author;
   private TextView url;
   private TextView source;
   private ImageView barBackground;
   private ProgressBar progressBar;
   private String reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        Intent mIntent = getIntent();
        initUi(mIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void initUi(Intent mIntent) {
        date = findViewById(R.id.news_date);
        date.setText(mIntent.getStringExtra("publishedAt"));

        content = findViewById(R.id.news_details);
        content.setText(mIntent.getStringExtra("content"));

        try {
            barBackground = findViewById(R.id.in_backdrop);
            Picasso
                    .get()
                    .load(mIntent.getStringExtra("urlToImage"))
                    .fit()
                    .into(barBackground);
            progressBar = findViewById(R.id.pb_load_backdrop);
            progressBar.setVisibility(View.GONE);

        }catch (Exception e){
            e.printStackTrace();
        }

        title = findViewById(R.id.title_details);
        title.setText(mIntent.getStringExtra("title"));

        url = findViewById(R.id.news_link);
        url.setText("Read the whole post");
        reference = mIntent.getStringExtra("url");
        url.setOnClickListener(this);

        author = findViewById(R.id.author_text);
        author.setText(mIntent.getStringExtra("author"));

        source = findViewById(R.id.source_text);
        source.setText(mIntent.getStringExtra("source"));
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("News");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,BrowserActivity.class);
        intent.setData(Uri.parse(reference));
        System.out.println(url.getText().toString());
        startActivity(intent);
    }
}
