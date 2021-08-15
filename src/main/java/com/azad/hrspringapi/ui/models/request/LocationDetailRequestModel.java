package com.azad.hrspringapi.ui.models.request;

public class LocationDetailRequestModel {

    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;

    public LocationDetailRequestModel() {
    }

    public LocationDetailRequestModel(String streetAddress, String postalCode, String city, String stateProvince) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
}
