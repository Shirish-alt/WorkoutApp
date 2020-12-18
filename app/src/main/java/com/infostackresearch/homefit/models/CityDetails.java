package com.infostackresearch.homefit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityDetails {
    @SerializedName("current_page")
    String current_page;

    @SerializedName("data")
    List<StatesAndCities> citiesList;

    public CityDetails(String current_page, List<StatesAndCities> citiesList) {
        this.current_page = current_page;
        this.citiesList = citiesList;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public List<StatesAndCities> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<StatesAndCities> citiesList) {
        this.citiesList = citiesList;
    }
}
