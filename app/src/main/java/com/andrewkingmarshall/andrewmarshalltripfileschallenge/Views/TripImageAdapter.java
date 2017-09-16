package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Views;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter used to display all the Trip Image Objects in the RecyclerView.
 */
public class TripImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface TripImageAdapterActionListener {
        void onHeartClicked(Image image);
        void onTextBubbleClicked(Image image);
        void onOverflowButtonClicked(Image image);
    }

    /**
     * The Image Objects we are showing.
     */
    private ArrayList<Image> imageObjects;

    private TripImageAdapterActionListener actionListener;

    private int screenWidth;

    public TripImageAdapter(@NonNull Context context, @NonNull List<Image> mediaObjects, @NonNull TripImageAdapterActionListener actionListener) {
        this.imageObjects = new ArrayList<>(mediaObjects);
        this.actionListener = actionListener;
        this.screenWidth = determineWidthOfScreen(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tripImageView = new TripImageView(parent.getContext());
        return new TripImageItemViewHolder(tripImageView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        TripImageItemViewHolder tripImageItemViewHolder = (TripImageItemViewHolder) holder;
        TripImageView tripImageView = tripImageItemViewHolder.tripImageView;

        Image tripImage = imageObjects.get(holder.getAdapterPosition());

        // Set the View with using the stored list of Media Objects
        tripImageView.setTripFileImage(tripImage, false, screenWidth, new TripImageView.TripImageViewActionListener() {
            @Override
            public void onHeartClicked(Image image) {
                actionListener.onHeartClicked(image);
            }

            @Override
            public void onTextBubbleClicked(Image image) {
                actionListener.onTextBubbleClicked(image);
            }

            @Override
            public void onOverflowButtonClicked(Image image) {
                actionListener.onOverflowButtonClicked(image);
            }
        });
    }

    /**
     * Determines the width of the screen, needed in Animations.
     */
    private int determineWidthOfScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    @Override
    public int getItemCount() {
        return  imageObjects.size();
    }

    //********************************************************************
    //********************** View Holders Below **************************
    //********************************************************************

    public class TripImageItemViewHolder extends RecyclerView.ViewHolder {

        TripImageView tripImageView;

        TripImageItemViewHolder(View itemView) {
            super(itemView);

            tripImageView = (TripImageView) itemView;
        }
    }
}
