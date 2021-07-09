package com.example.parstagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.parstagram.R;
import com.example.parstagram.models.Post;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvDetailsUsername;
    private ImageView ivDetailsImage;
    private TextView tvDetailsDescription;
    private TextView tvDetailsDate;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);


        tvDetailsUsername = findViewById(R.id.tvDetailsUsername);
        ivDetailsImage = findViewById(R.id.ivDetailsImage);
        tvDetailsDescription = findViewById(R.id.tvDetailsDescription);
        tvDetailsDate = findViewById(R.id.tvDetailsDate);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));


        // Bind the post data to the view elements
        tvDetailsDescription.setText(post.getDescription());
        tvDetailsUsername.setText(post.getUser().getUsername());

        Date createdAt = post.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);

        tvDetailsDate.setText(timeAgo);

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(DetailsActivity.this).load(image.getUrl()).into(ivDetailsImage);
        }

    }
}