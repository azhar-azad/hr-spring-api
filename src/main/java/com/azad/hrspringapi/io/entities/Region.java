package com.azad.hrspringapi.io.entities;

public class Region {

    private Long id;
    private String regionName;

    // mapping with Country

    public Region() {
    }

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public Long getId() {
        return id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
