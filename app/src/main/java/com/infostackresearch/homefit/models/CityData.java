package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class CityData {
    @SerializedName("success")
    boolean success;
    @SerializedName("data")
    CityDetails cityDetails;

    public CityData(boolean success, CityDetails cityDetails) {
        this.success = success;
        this.cityDetails = cityDetails;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CityDetails getCityDetails() {
        return cityDetails;
    }

    public void setCityDetails(CityDetails cityDetails) {
        this.cityDetails = cityDetails;
    }
}
