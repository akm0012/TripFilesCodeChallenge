package com.andrewkingmarshall.andrewmarshalltripfileschallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Networking.TripFilesFetchTask;
import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects.Image;
import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects.TripFile;
import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Views.TripImageAdapter;

public class MainActivity extends AppCompatActivity implements TripFilesFetchTask.TripFileFetchListener {

    private RecyclerView tripFileImageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TripFilesFetchTask(MainActivity.this).execute();

        // Set up the RecyclerView
        tripFileImageRecyclerView = (RecyclerView) findViewById(R.id.tripFileImageRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tripFileImageRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void tripFileRetrieved(TripFile tripFile) {

        // Set the adapter and show the data in the RecyclerView
        TripImageAdapter tripImageAdapter = new TripImageAdapter(this, tripFile.getImages(), new TripImageAdapter.TripImageAdapterActionListener() {
            @Override
            public void onHeartClicked(Image image) {
                Toast.makeText(MainActivity.this, image.getImageId() + " heart clicked.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextBubbleClicked(Image image) {
                Toast.makeText(MainActivity.this, image.getImageId() + " comment bubble clicked.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOverflowButtonClicked(Image image) {
                Toast.makeText(MainActivity.this, image.getImageId() + " overflow clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        tripFileImageRecyclerView.setAdapter(tripImageAdapter);
    }
}
