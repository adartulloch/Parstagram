package com.example.parstagram.activities;

import android.app.Application;

import com.example.parstagram.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NuYxCJ4MZvDGHNHpIFB8eO2PLzeskz12X845oywL")
                .clientKey("1xSGRnVbqqnjsVRladndSFpxdeY2QMMnMEsj1RJd")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
