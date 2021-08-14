package com.azad.hrspringapi.io.entities;

public class Country {

    private Long id;
    private String countryName;

    // mapping with Region

    // mapping with Location

    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Long getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
