package com.azad.hrspringapi.shared.dto;

public class RegionDto {

    private Long id;
    private String regionName;

    public RegionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toString() {
        return "RegionDto{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
