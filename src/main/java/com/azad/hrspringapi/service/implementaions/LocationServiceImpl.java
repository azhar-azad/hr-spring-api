package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.LocationServiceException;
import com.azad.hrspringapi.io.entities.Country;
import com.azad.hrspringapi.io.entities.Location;
import com.azad.hrspringapi.io.repositories.LocationRepository;
import com.azad.hrspringapi.service.CountryService;
import com.azad.hrspringapi.service.LocationService;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    private CountryService countryService;

    public LocationServiceImpl(LocationRepository locationRepository, CountryService countryService) {
        this.locationRepository = locationRepository;
        this.countryService = countryService;
    }

    @Override
    public Location createLocation(Location location, Long countryId) {
        Country country = countryService.getCountryById(countryId);
        if (country == null) {
            throw new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + countryId);
        }

        location.setCountry(country);

        return locationRepository.save(location);
    }

    @Override
    public List<Location> getLocations(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("city"));
        List<Location> locations = locationRepository.findAll(pageable).getContent();

        if (locations.size() == 0) {
            throw  new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());
        }

        return locations;
    }

    @Override
    public List<Location> getLocationsByCountry(int page, int limit, Long countryId) {
        Country country = countryService.getCountryById(countryId);
        if (country == null) {
            throw new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + countryId);
        }

        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("city"));
        List<Location> locations = locationRepository.findAllByCountry(country, pageable).getContent();

        if (locations.size() == 0) {
            throw  new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());
        }

        return locations;
    }

    @Override
    public Location getLocationById(Long id) {
        Location location = locationRepository.findById(id).orElse(null);

        if (location == null)
            throw new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        return location;
    }

    @Override
    public Location updateLocation(Long id, Location updatedLocationData) {

        Location location = locationRepository.findById(id).orElse(null);

        if (location == null)
            throw new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        if (updatedLocationData.getStreetAddress() != null && !updatedLocationData.getStreetAddress().equals(""))
            location.setStreetAddress(updatedLocationData.getStreetAddress());
        if (updatedLocationData.getPostalCode() != null && !updatedLocationData.getPostalCode().equals(""))
            location.setPostalCode(updatedLocationData.getPostalCode());
        if (updatedLocationData.getCity() != null && !updatedLocationData.getCity().equals(""))
            location.setCity(updatedLocationData.getCity());
        if (updatedLocationData.getStateProvince() != null && !updatedLocationData.getStateProvince().equals(""))
            location.setStreetAddress(updatedLocationData.getStateProvince());

        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id).orElse(null);

        if (location == null)
            throw new LocationServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        locationRepository.delete(location);
    }
}
