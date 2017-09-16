package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A Trip File Image object.
 */
public class Image implements Serializable, Comparable<Image>{

    @SerializedName("TripCrewItemInfo")
    private TripCrewItemInfo tripCrewItemInfo;

    @SerializedName("CoverImage")
    private boolean isCoverImage;

    @SerializedName("ImageId")
    private String imageId;

    @SerializedName("ImageTemplate")
    private String imageTemplate;

    @SerializedName("Width")
    private int width;

    @SerializedName("Height")
    private int height;

    @SerializedName("CommentsCount")
    private int commentsCount;

    @SerializedName("LikesCount")
    private int likesCount;

    @SerializedName("IsLiked")
    private boolean isLiked;

    @SerializedName("ChangeDate")
    private long changeDate;

    @SerializedName("Title")
    private String title;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("Location")
    private Location location;

    @SerializedName("Timestamp")
    private long timestamp;

    @SerializedName("BatchId")
    private String batchId;

    public TripCrewItemInfo getTripCrewItemInfo() {
        return tripCrewItemInfo;
    }

    public void setTripCrewItemInfo(TripCrewItemInfo tripCrewItemInfo) {
        this.tripCrewItemInfo = tripCrewItemInfo;
    }

    public boolean isCoverImage() {
        return isCoverImage;
    }

    public void setCoverImage(boolean coverImage) {
        isCoverImage = coverImage;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageTemplate() {
        return imageTemplate;
    }

    public void setImageTemplate(String imageTemplate) {
        this.imageTemplate = imageTemplate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public long getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(long changeDate) {
        this.changeDate = changeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    // Overriding the compareTo method
    public int compareTo(Image image) {
        return Long.compare(this.timestamp, image.timestamp);
    }
}

