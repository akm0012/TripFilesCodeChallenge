package com.andrewkingmarshall.andrewmarshalltripfileschallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Networking.TripFilesFetchTask;
import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects.TripFile;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TripFilesFetchTask.TripFileFetchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TripFilesFetchTask(MainActivity.this).execute();
    }

    @Override
    public void tripFileRetrieved(TripFile tripFile) {


        Log.d("akm", "Trip files size: " + tripFile.getImages().size());

    }
}
