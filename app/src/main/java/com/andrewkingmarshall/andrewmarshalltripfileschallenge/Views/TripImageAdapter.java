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

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
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

    public TripImageAdapter(@NonNull Context context, @NonNull List<Image> imageList, @NonNull TripImageAdapterActionListener actionListener) {

        imageList.get(2).setTimestamp(1505526802155L);

        // Sort the Images by timestamp
        Collections.sort(imageList);

        this.imageObjects = new ArrayList<>(imageList);
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

        int adapterPosition = holder.getAdapterPosition();

        Image tripImage = imageObjects.get(adapterPosition);

        boolean showHeader = shouldHeaderBeDisplayed(adapterPosition, tripImage);

        // Set the View with using the stored list of Media Objects
        tripImageView.setTripFileImage(tripImage, showHeader, screenWidth, new TripImageView.TripImageViewActionListener() {
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
     * Holds the logic to determine if the Header should be displayed.
     *
     * @param adapterPosition The position of the Image.
     * @param tripImage The Trip Image Object.
     * @return Flag to indicate if we should show the Date Header or not.
     */
    private boolean shouldHeaderBeDisplayed(int adapterPosition, Image tripImage) {

        boolean showHeader = false;

        // Always show the header if this is the first element.
        if (adapterPosition == 0) {
            showHeader = true;
        } else {

            // Otherwise, we show the Date Header if the current Image is newer (by a day) than the previous image.

            DateTime previousImageTime = new DateTime(imageObjects.get(adapterPosition - 1).getTimestamp());
            DateTime thisImageTime = new DateTime(tripImage.getTimestamp());

            // First check if the years are different
            if (thisImageTime.getYear() > previousImageTime.getYear()) {
                showHeader = true;
            } else {
                // Then check the day in that year
                if (thisImageTime.getDayOfYear() > previousImageTime.getDayOfYear()) {
                    showHeader = true;
                }
            }
        }
        return showHeader;
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
