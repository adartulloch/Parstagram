package com.example.parstagram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.parstagram.R;
import com.example.parstagram.activities.LoginActivity;
import com.example.parstagram.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    public static final String TAG = "PostsFragment";
    private static final int QUERY_LIMIT = 20;
    private Button btnLogout;

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setVisibility(View.VISIBLE);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //User will logout of the application
                ParseUser currentUser = ParseUser.getCurrentUser();
                currentUser.logOut();

                //Go back to the Login Screen
                Intent i = new Intent(getContext(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    @Override
    protected void queryPosts() {
        //Specify which type of data we want to query - Post.class
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        //Fetch data only when the current user matches the Post user info
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        //Include the data referred by user key
        query.include(Post.KEY_USER);

        //Limit query
        query.setLimit(QUERY_LIMIT);

        //Order posts by creation date
        query.addDescendingOrder("createdAt");

        //Fetch the 'Posts' data asynchronously
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                //Ensure there are no errors
                if (e != null) {
                    Log.e(TAG, "Error fetching posts", e);
                }

                //For debugging purposes, we will print all posts to Logcat
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

                //Save the posts to list and notify the adapter of new data
                postsAdapter.clear();
                allPosts.addAll(posts);
                postsAdapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
