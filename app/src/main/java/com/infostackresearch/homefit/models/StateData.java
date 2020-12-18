package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateData {
    @SerializedName("success")
    boolean success;
    @SerializedName("data")
    List<StatesAndCities> states_and_cities;
    @SerializedName("message")
    String message;


    public StateData(boolean success, List<StatesAndCities> states_and_cities, String message) {
        this.success = success;
        this.states_and_cities = states_and_cities;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<StatesAndCities> getStates_and_cities() {
        return states_and_cities;
    }

    public void setStates_and_cities(List<StatesAndCities> states_and_cities) {
        this.states_and_cities = states_and_cities;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
