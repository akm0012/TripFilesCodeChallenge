package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A Trip Crew Item Owner.
 */
public class TripCrewItemOwner implements Serializable {

    @SerializedName("UserId")
    private String userId;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}