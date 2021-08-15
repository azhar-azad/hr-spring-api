package com.azad.hrspringapi.service;

import com.azad.hrspringapi.io.entities.Location;

import java.util.List;

public interface LocationService {

    Location createLocation(Location location, Long countryId);

    List<Location> getLocations(int page, int limit);

    List<Location> getLocationsByCountry(int page, int limit, Long countryId);

    Location getLocationById(Long id);

    Location updateLocation(Long id, Location updatedLocationData);

    void deleteLocation(Long id);
}
