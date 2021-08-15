package com.azad.hrspringapi.shared.dto;

import com.azad.hrspringapi.ui.models.response.RegionRestResponse;

public class CountryDto {

    private Long id;
    private String countryName;
    private Long regionId;
    private RegionRestResponse region;

    public CountryDto() {
    }

    public CountryDto(Long id, String countryName) {
        this.id = id;
        this.countryName = countryName;
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

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public RegionRestResponse getRegion() {
        return region;
    }

    public void setRegion(RegionRestResponse region) {
        this.region = region;
    }
}
