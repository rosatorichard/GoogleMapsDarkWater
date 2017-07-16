package com.batchmates.android.darkwaters.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Android on 7/15/2017.
 */

public class ClosePlaces {
    String placeName;
    String placeAddress;
    String phoneNumber;
    String PlaceAtributes;
    LatLng latLngLocation;
    int priceLvl;
    float stars;

    public ClosePlaces(String placeName, String placeAddress, String placeAtributes, LatLng latLngLocation, int priceLvl, float stars,String phoneNumber) {
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.PlaceAtributes = placeAtributes;
        this.latLngLocation = latLngLocation;
        this.priceLvl=priceLvl;
        this.stars=stars;
        this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getPriceLvl() {
        return priceLvl;
    }

    public float getStars() {
        return stars;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public String getPlaceAtributes() {
        return PlaceAtributes;
    }

    public LatLng getLatLngLocation() {
        return latLngLocation;
    }
}
