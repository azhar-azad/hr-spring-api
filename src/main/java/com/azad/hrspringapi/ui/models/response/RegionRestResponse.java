package com.azad.hrspringapi.ui.models.response;

public class RegionRestResponse {

    private Long id;
    private String regionName;

    public RegionRestResponse() {
    }

    public RegionRestResponse(Long id, String regionName) {
        this.id = id;
        this.regionName = regionName;
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
        return "RegionRestResponse{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
