package com.azad.hrspringapi.ui.models.response;

public class CountryRestResponse {

    private Long id;
    private String countryName;
    private RegionRestResponse region;

    public CountryRestResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public RegionRestResponse getRegion() {
        return region;
    }

    public void setRegion(RegionRestResponse region) {
        this.region = region;
    }
}
