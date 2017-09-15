package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andrewkingmarshall.andrewmarshalltripfileschallenge.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This View represents a Trip Image.
 *
 * Designed with use in a RecyclerView in mind. Should reset it's view when set.
 */
public class TripImageView extends LinearLayout {

    @BindView(R.id.dateHeader) View dateHeader;
    @BindView(R.id.dayTextView) TextView dayTextView;
    @BindView(R.id.dateTextView) TextView dateTextView;

    @BindView(R.id.mediaImageView) ImageView mediaImageView;
    @BindView(R.id.commentsTextViewCounter) TextView commentsTextViewCounter;
    @BindView(R.id.likesTextViewCounter) TextView likesTextViewCounter;

    @BindView(R.id.heartImage) ImageView heartImage;
    @BindView(R.id.commentImage) ImageView commentImage;
    @BindView(R.id.threeDots) ImageView threeDots;
    @BindView(R.id.timeStamp) TextView timeStamp;

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

    private void resetView() {

    }
}
