package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Networking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects.TripFile;
import com.andrewkingmarshall.andrewmarshalltripfileschallenge.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This Async Task will retrieve all the Trip File Objects located at: http://www.gregframe.com/pubimage/tfDataExample.php
 *
 * Note: Normally I would build out an entire robust network and data layer using Retrofit and Realm,
 * but this will work great for the purposes of this exercise.
 *
 */

public class TripFilesFetchTask extends AsyncTask<Void, Void, TripFile> {

    private static final String TAG = TripFilesFetchTask.class.getSimpleName();

    private TripFileFetchListener fetchListener;

    private String dataURL;

    public interface TripFileFetchListener {
        void tripFileRetrieved(TripFile tripFile);
    }

    public TripFilesFetchTask(Context context) {

        if (context instanceof TripFileFetchListener) {
            this.fetchListener = (TripFileFetchListener) context;
        } else {
            throw new IllegalArgumentException("The Context must implement TripFileFetchListener.");
        }

        dataURL = context.getString(R.string.dataEndpointUrl);
    }

    @Override
    protected TripFile doInBackground(Void... voids) {

        // The raw Json String we will get from the URL
        String data;

        // The list of media objects we will be returning
        TripFile tripFile = new TripFile();

        try {
            URL url = new URL(dataURL);

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

            // Fix in the JSON data
            data = data.substring(0, data.length() - 3);


            // Clean up
            inputStream.close();
            conn.disconnect();

            // Convert the raw Json into a List of Media Objects
            Gson gson = new Gson();
            tripFile = gson.fromJson(data, new TypeToken<TripFile>(){}.getType());

        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getLocalizedMessage(), new Throwable());
            e.printStackTrace();
        }

        Log.d(TAG, "Number of Images retrieved: " + tripFile.getImages().size());

        return tripFile;
    }

    @Override
    protected void onPostExecute(TripFile tripFile) {
        super.onPostExecute(tripFile);

        // Return the Media Objects to the Listener
        fetchListener.tripFileRetrieved(tripFile);
    }
}
