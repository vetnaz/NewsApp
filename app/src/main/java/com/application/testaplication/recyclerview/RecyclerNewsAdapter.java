package com.application.testaplication.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.testaplication.R;
import com.application.testaplication.newsscreen.view.FirstActivity;
import com.application.testaplication.pojo.Message;
import com.application.testaplication.revisedscreen.view.RevisedActivity;
import com.application.testaplication.searchscreen.view.SearchActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerNewsAdapter extends RecyclerView.Adapter<RecyclerNewsAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
    private FirstActivity firstActivity;
    private SearchActivity searchActivity;
    private RevisedActivity revisedActivity;

    private final List<Message> mDataSet;

    public RecyclerNewsAdapter(List<Message> messages,FirstActivity firstActivity) {
        this.firstActivity=firstActivity;
        this.mDataSet = messages;
    }

    public RecyclerNewsAdapter(List<Message> messages, SearchActivity searchActivity) {
        this.searchActivity=searchActivity;
        this.mDataSet = messages;
    }

    public RecyclerNewsAdapter(List<Message> messages, RevisedActivity revisedActivity) {
        this.revisedActivity=revisedActivity;
        this.mDataSet = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerNewsAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        Message message = mDataSet.get(position);

        holder.newsTitle.setText(message.getTitle());
        holder.newsAuthor.setText(message.getAuthor());
        holder.newsDescription.setText(message.getDescription());
        try{
            Picasso
                    .get()
                    .load(message.getUrlToImage())
                    .fit()
                    .into(holder.imageView);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(view -> {
            if (searchActivity!=null) {
                searchActivity.onNewsClickListener(position);
            }
            if (firstActivity!=null){
                firstActivity.onNewsClickListener(position);
            }
            if (revisedActivity!=null){
                revisedActivity.onNewsClickListener(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView newsAuthor;
        final TextView newsTitle;
        final TextView newsDescription;
        final ImageView imageView;


        ViewHolder(View v) {
            super(v);

            imageView = v.findViewById(R.id.imageNewsView);
            newsAuthor = v.findViewById(R.id.newsAuthor);
            newsTitle = v.findViewById(R.id.newsTitle);
            newsDescription = v.findViewById(R.id.newsDescriptions);

            v.setOnClickListener(v1 -> Log.d(TAG, "Element " + getAdapterPosition() + " clicked."));

        }

        public TextView getNewsAuthor() {
            return newsAuthor;
        }

        public TextView getNewsTitle() {
            return newsTitle;
        }

        public TextView getNewsDescription() {
            return newsDescription;
        }

    }


}
