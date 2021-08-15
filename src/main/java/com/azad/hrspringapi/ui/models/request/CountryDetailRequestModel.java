package com.azad.hrspringapi.ui.models.request;

public class CountryDetailRequestModel {

    private String countryName;

    public CountryDetailRequestModel() {
    }

    public CountryDetailRequestModel(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
