package com.azad.hrspringapi.io.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String regionName;

    @OneToMany(
            mappedBy = "region",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Country> countries;

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

    public Set<Country> getCountries() {
        return countries;
    }

//    public void addCountry(Country country) {
//        if (country != null) {
//            if (this.countries == null) {
//                this.countries = new HashSet<>();
//            }
//            country.setRegion(this);
//            this.countries.add(country);
//        }
//    }
//
//    public void addCountries(Set<Country> countries) {
//        if (countries.size() != 0) {
//            countries.forEach(country -> country.setRegion(this));
//            this.countries = countries;
//        }
//    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
