package com.azad.hrspringapi.ui.models.response;

public class LocationRestResponse {

    private Long id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private CountryRestResponse country;

    public LocationRestResponse() {
    }

    public LocationRestResponse(Long id, String streetAddress, String postalCode, String city, String stateProvince) {
        this.id = id;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CountryRestResponse getCountry() {
        return country;
    }

    public void setCountry(CountryRestResponse country) {
        this.country = country;
    }
}
