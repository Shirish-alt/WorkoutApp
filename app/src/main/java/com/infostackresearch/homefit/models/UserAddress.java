package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class UserAddress {
    @SerializedName("message")
    String message;
    @SerializedName("success")
    boolean success;
    @SerializedName("data")
    DeliveryLocation deliveryLocation;

    public UserAddress(String message, boolean success, DeliveryLocation deliveryLocation) {
        this.message = message;
        this.success = success;
        this.deliveryLocation = deliveryLocation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DeliveryLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
}
