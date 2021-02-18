package com.application.testaplication.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"title"},
        unique = true)})
public class MessageEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String source;
    public String publishedAt;
    public String content;
}
