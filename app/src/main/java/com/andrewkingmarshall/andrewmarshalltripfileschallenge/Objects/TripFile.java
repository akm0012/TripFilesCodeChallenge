package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a Trip File object.
 *
 * Note: For the purpose of this exercise, only the necessary information is included.
 *
 */
public class TripFile implements Serializable {

    @SerializedName("Images")
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
