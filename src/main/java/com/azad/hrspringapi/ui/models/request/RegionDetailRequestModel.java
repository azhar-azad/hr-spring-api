package com.azad.hrspringapi.ui.models.request;

public class RegionDetailRequestModel {

    private String regionName;

    public RegionDetailRequestModel() {
    }

    public RegionDetailRequestModel(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "RegionDetailRequestModel{" +
                "regionName='" + regionName + '\'' +
                '}';
    }
}
