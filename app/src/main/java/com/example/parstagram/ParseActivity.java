package com.example.parstagram;

import android.app.Application;
import com.parse.Parse;

public class ParseActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NuYxCJ4MZvDGHNHpIFB8eO2PLzeskz12X845oywL")
                .clientKey("1xSGRnVbqqnjsVRladndSFpxdeY2QMMnMEsj1RJd")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
