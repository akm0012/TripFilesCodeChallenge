package com.andrewkingmarshall.andrewmarshalltripfileschallenge;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This Async Task will retrieve all the Trip File Objects located at: http://www.gregframe.com/pubimage/tfDataExample.php
 *
 * Note: Normally I would build out an entire robust network and data layer using Retrofit and Realm,
 * but this work great for the purposes of this exercise.
 *
 */

public class TripFilesFetchTask extends AsyncTask<Void, Void, List<TripFile>> {

    private static final String TAG = TripFilesFetchTask.class.getSimpleName();

    private MediaFetchListener mediaListener;

    public interface MediaFetchListener {
        void mediaRetrieved(List<TripFile> mediaObjects);
    }

    public TripFilesFetchTask(Context context) {

        if (context instanceof MediaFetchListener) {
            this.mediaListener = (MediaFetchListener) context;
        } else {
            throw new IllegalArgumentException("The Context must implement MediaFetchListener.");
        }
    }

    @Override
    protected List<TripFile> doInBackground(Void... voids) {

        String mediaUrl = "http://de-coding-test.s3.amazonaws.com/books.json";

        // The raw Json String we will get from the URL
        String data;

        // The list of media objects we will be returning
        ArrayList<TripFile> mediaObjects = new ArrayList<>();

        try {
            URL url = new URL(mediaUrl);

            // Connect to the URL where all our data is located
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            // Start getting data
            InputStream inputStream = conn.getInputStream();

            // Build the Data String using a String Builder
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }

            data = stringBuilder.toString();

            // Clean up
            inputStream.close();
            conn.disconnect();

            // Convert the raw Json into a List of Media Objects
            Gson gson = new Gson();
            mediaObjects = gson.fromJson(data, new TypeToken<List<TripFile>>(){}.getType());

        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getLocalizedMessage(), new Throwable());
            e.printStackTrace();
        }

        Log.d(TAG, "Number of Media Objects retrieved: " + mediaObjects.size());

        return mediaObjects;
    }

    @Override
    protected void onPostExecute(List<TripFile> tripFiles) {
        super.onPostExecute(tripFiles);

        // Return the Media Objects to the Listener
        mediaListener.mediaRetrieved(tripFiles);
    }
}
