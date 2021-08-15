package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.io.entities.Location;
import com.azad.hrspringapi.service.LocationService;
import com.azad.hrspringapi.ui.models.request.LocationDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.LocationRestResponse;
import com.azad.hrspringapi.ui.models.response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {

    private ModelMapper modelMapper = new ModelMapper();

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(path = "/locations/test")
    public String locationRouterTest() {
        return "Location controller is up and running";
    }

    @PostMapping(path = "/countries/{countryId}/locations")
    public ResponseEntity<LocationRestResponse> createLocation(@RequestBody LocationDetailRequestModel locationDetail, @PathVariable Long countryId) {
        Location location = modelMapper.map(locationDetail, Location.class);

        Location createdLocation = locationService.createLocation(location, countryId);

        LocationRestResponse responseBody = modelMapper.map(createdLocation, LocationRestResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(path = "/locations")
    public ResponseEntity<List<LocationRestResponse>> getLocations(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<Location> locations = locationService.getLocations(page, limit);

        List<LocationRestResponse> locationResponseList = new ArrayList<>();
        locations.forEach(location -> locationResponseList.add(modelMapper.map(location, LocationRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(locationResponseList);
    }

    @GetMapping(path = "/countries/{countryId}/locations")
    public ResponseEntity<List<LocationRestResponse>> getLocationsByCountry(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @PathVariable Long countryId) {

        List<Location> locations = locationService.getLocationsByCountry(page, limit, countryId);

        List<LocationRestResponse> locationResponseList = new ArrayList<>();
        locations.forEach(location -> locationResponseList.add(modelMapper.map(location, LocationRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(locationResponseList);
    }

    @GetMapping(path = "/locations/{id}")
    public ResponseEntity<LocationRestResponse> getLocation(@PathVariable Long id) {
        Location location = locationService.getLocationById(id);

        LocationRestResponse responseBody = modelMapper.map(location, LocationRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/locations/{id}")
    public ResponseEntity<LocationRestResponse> updateLocation(@PathVariable Long id, @RequestBody LocationDetailRequestModel locationDetails) {
        Location location = modelMapper.map(locationDetails, Location.class);

        Location updatedLocation = locationService.updateLocation(id, location);

        LocationRestResponse responseBody = modelMapper.map(updatedLocation, LocationRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(path = "/locations/{id}")
    public ResponseEntity<OperationStatusModel> deleteLocation(@PathVariable Long id) {
        OperationStatusModel responseBody = new OperationStatusModel();
        responseBody.setOperationName("DELETE");
        locationService.deleteLocation(id);
        responseBody.setOperationResult("SUCCESS");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
