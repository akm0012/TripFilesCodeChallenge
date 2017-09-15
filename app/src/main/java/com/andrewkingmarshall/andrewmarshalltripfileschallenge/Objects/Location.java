package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A Location as expressed by geo-coordinates.
 */
public class Location implements Serializable {

    @SerializedName("Latitude")
    private double latitude;

    @SerializedName("Longitude")
    private double longitude;

    @SerializedName("GpPlaceId")
    private String gpPlaceId;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGpPlaceId() {
        return gpPlaceId;
    }

    public void setGpPlaceId(String gpPlaceId) {
        this.gpPlaceId = gpPlaceId;
    }
}
