package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * The Profile Image object.
 */
public class ProfileImage implements Serializable {

    @SerializedName("ImageId")
    private String imageId;

    @SerializedName("ImageTemplate")
    private String imageTemplate;

    @SerializedName("Width")
    private int width;

    @SerializedName("Height")
    private int height;

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
}
