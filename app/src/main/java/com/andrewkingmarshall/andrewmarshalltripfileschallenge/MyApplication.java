package com.andrewkingmarshall.andrewmarshalltripfileschallenge;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * The Application.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Needed to manipulate times
        JodaTimeAndroid.init(this);
    }
}
