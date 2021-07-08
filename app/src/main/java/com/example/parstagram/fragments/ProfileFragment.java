package com.example.parstagram.fragments;

import android.util.Log;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.parstagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    public static final String TAG = "PostsFragment";
    private static final int QUERY_LIMIT = 20;

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
