package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A Location as expressed by geo-coordinates.
 */
public class Location implements Serializable {

    @SerializedName("Latitude")
    private long latitude;

    @SerializedName("Longitude")
    private long longitude;

    @SerializedName("GpPlaceId")
    private String gpPlaceId;

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getGpPlaceId() {
        return gpPlaceId;
    }

    public void setGpPlaceId(String gpPlaceId) {
        this.gpPlaceId = gpPlaceId;
    }
}
