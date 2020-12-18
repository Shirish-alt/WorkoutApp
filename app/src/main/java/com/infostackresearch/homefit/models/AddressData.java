package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressData {
    @SerializedName("success")
    boolean success;

    @SerializedName("message")
    String message;

    @SerializedName("data")
    List<DeliveryLocation> deliveryLocationList;

    public AddressData(boolean success, String message, List<DeliveryLocation> deliveryLocationList) {
        this.success = success;
        this.message = message;
        this.deliveryLocationList = deliveryLocationList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DeliveryLocation> getDeliveryLocationList() {
        return deliveryLocationList;
    }

    public void setDeliveryLocationList(List<DeliveryLocation> deliveryLocationList) {
        this.deliveryLocationList = deliveryLocationList;
    }
}
