package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

public class StatesAndCities {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("code")
    String code;
    @SerializedName("state_id")
    String state_id;

    public StatesAndCities(String id, String name, String code, String state_id) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.state_id = state_id;
    }

    public StatesAndCities(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.code;
    }
}
