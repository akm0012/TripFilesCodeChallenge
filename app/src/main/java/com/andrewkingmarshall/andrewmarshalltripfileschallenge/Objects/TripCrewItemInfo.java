package com.andrewkingmarshall.andrewmarshalltripfileschallenge.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A Trip Crew Item Info object.
 */
public class TripCrewItemInfo implements Serializable {

    @SerializedName("TripCrewItemOwner")
    private TripCrewItemOwner tripCrewItemOwner;

    @SerializedName("ProfileImage")
    private ProfileImage profileImage;

    @SerializedName("IsItemOwner")
    private boolean isItemOwner;

    public TripCrewItemOwner getTripCrewItemOwner() {
        return tripCrewItemOwner;
    }

    public void setTripCrewItemOwner(TripCrewItemOwner tripCrewItemOwner) {
        this.tripCrewItemOwner = tripCrewItemOwner;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isItemOwner() {
        return isItemOwner;
    }

    public void setItemOwner(boolean itemOwner) {
        isItemOwner = itemOwner;
    }
}
