package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class DeliveryLocation {
    @SerializedName("id")
    String addressid;

    @SerializedName("address")
    String address;

    @SerializedName("name")
    String name;

    @SerializedName("state_id")
    String state_id;

    @SerializedName("city_id")
    String city_id;

    @SerializedName("pincode")
    String pincode;

    @SerializedName("phone")
    String phone;

    @SerializedName("state")
    StatesAndCities state;

    @SerializedName("city")
    StatesAndCities city;

    public DeliveryLocation(String addressid, String address, String name, String state_id, String city_id, String pincode, String phone, StatesAndCities state, StatesAndCities city) {
        this.addressid = addressid;
        this.address = address;
        this.name = name;
        this.state_id = state_id;
        this.city_id = city_id;
        this.pincode = pincode;
        this.phone = phone;
        this.state = state;
        this.city = city;
    }

    public DeliveryLocation(String address, String name, String state_id, String city_id, String pincode, String phone) {
        this.address = address;
        this.name = name;
        this.state_id = state_id;
        this.city_id = city_id;
        this.pincode = pincode;
        this.phone = phone;
    }

    public DeliveryLocation(String addressid, String address, String name, String state_id, String city_id, String pincode, String phone) {
        this.addressid = addressid;
        this.address = address;
        this.name = name;
        this.state_id = state_id;
        this.city_id = city_id;
        this.pincode = pincode;
        this.phone = phone;
    }

    public DeliveryLocation(String addressid, String address, String name) {
        this.addressid = addressid;
        this.address = address;
        this.name = name;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StatesAndCities getState() {
        return state;
    }

    public void setState(StatesAndCities state) {
        this.state = state;
    }

    public StatesAndCities getCity() {
        return city;
    }

    public void setCity(StatesAndCities city) {
        this.city = city;
    }
}
