package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects.Image;
import com.andrewkingmarshall.andrewmarshalltripfileschallenge.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This View represents a Trip Image.
 *
 * Designed with use in a RecyclerView in mind. Should reset it's view when set.
 */
public class TripImageView extends LinearLayout {

    private int screenWidth;

    @BindView(R.id.dateHeader) View dateHeader;
    @BindView(R.id.dayTextView) TextView dayTextView;
    @BindView(R.id.dateTextView) TextView dateTextView;

    @BindView(R.id.mediaImageView) ImageView mainTripImageView;
    @BindView(R.id.commentsTextViewCounter) TextView commentsTextViewCounter;
    @BindView(R.id.likesTextViewCounter) TextView likesTextViewCounter;

    @BindView(R.id.heartImage) ImageView heartImage;
    @BindView(R.id.commentImage) ImageView commentImage;
    @BindView(R.id.threeDots) ImageView threeDots;
    @BindView(R.id.timeStamp) TextView timeStamp;

    public interface TripImageViewActionListener {
        void onHeartClicked(Image image);
        void onTextBubbleClicked(Image image);
        void onOverflowButtonClicked(Image image);
    }

    public TripImageView(Context context) {
        super(context);
        init();
    }

    public TripImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TripImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.trip_image_view, this);

        ButterKnife.bind(this);
    }

    /**
     * Resets the view back to a known starting state.
     */
    private void resetView() {
        dateHeader.setVisibility(GONE);
        dayTextView.setText(null);
        dateTextView.setText(null);

        mainTripImageView.setImageDrawable(null);
        commentsTextViewCounter.setText(null);
        likesTextViewCounter.setText(null);

        heartImage.setOnClickListener(null);
        commentImage.setOnClickListener(null);
        threeDots.setOnClickListener(null);
        timeStamp.setText(null);
    }

    /**
     * Sets all the Views to represent a Trip File Image.
     *
     * @param image The Trip File Image we want to set.
     */
    public void setTripFileImage(@NonNull final Image image, boolean showDateHeader, int screenWidth,
                                 @NonNull final TripImageViewActionListener actionListener) {

        resetView();

        // Create the image url and load the Image
        loadImage(image, screenWidth);

        // Check if we should show the Date Header
        if (showDateHeader) {

            // Format the Day (Should be all Caps and abbreviated. I.E. "WED")
            DateTimeFormatter dayFormatter = DateTimeFormat.forPattern("E");
            String day = dayFormatter.print(image.getTimestamp()).toUpperCase();

            // Format the Month (Should be "July 17, 1990")
            DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MMMM M, yyyy");
            String date = dateFormatter.print(image.getTimestamp());

            dayTextView.setText(day);
            dateTextView.setText(date);

            dateHeader.setVisibility(VISIBLE);
        }

        // Set the Comments text
        int commentCount = image.getCommentsCount();
        commentsTextViewCounter.setText(String.format(getContext().getResources().getQuantityString(R.plurals.comments, commentCount, commentCount), commentCount));

        // Set the Likes text
        int likesCount = image.getLikesCount();
        likesTextViewCounter.setText(String.format(getContext().getResources().getQuantityString(R.plurals.likes, likesCount, likesCount), likesCount));

        // Set the smaller TimeStamp
        // TODO: Not sure on format, using something generic. I.E. 7/17/1990
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(" M/d/yyyy");
        String date = dateFormatter.print(image.getTimestamp());
        timeStamp.setText(date);

        // Add all the onClickListeners
        heartImage.setOnClickListener(view -> actionListener.onHeartClicked(image));
        commentImage.setOnClickListener(view -> actionListener.onTextBubbleClicked(image));
        threeDots.setOnClickListener(view -> actionListener.onOverflowButtonClicked(image));
    }

    /**
     * Loads an image into the ImageView.
     *
     * @param image The image you want to load.
     * @param screenWidth The width of the screen.
     */
    private void loadImage(Image image, int screenWidth) {

        String photoUrl = getContext().getString(R.string.photoBaseURL) + image.getImageTemplate();

        // Load the Image with a place holder image (Added some edge case crash prevention)
        if (!((Activity) getContext()).isFinishing() && !((Activity) getContext()).isDestroyed()) {
            Glide.with(getContext())
                    .load(photoUrl)
                    .override(screenWidth, Target.SIZE_ORIGINAL)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .error(R.color.grey)
                    .placeholder(R.color.grey)
                    .into(mainTripImageView);
        }
    }
}
