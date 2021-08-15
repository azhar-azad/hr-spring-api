package com.azad.hrspringapi.io.entities;

import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(
            mappedBy = "country",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Location> locations;

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
