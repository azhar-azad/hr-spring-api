package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.io.entities.Country;
import com.azad.hrspringapi.service.CountryService;
import com.azad.hrspringapi.ui.models.request.CountryDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.CountryRestResponse;
import com.azad.hrspringapi.ui.models.response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    private ModelMapper modelMapper = new ModelMapper();

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(path = "/countries/test")
    public String countryRouterTest() {
        return "Country controller is up and running";
    }

    @PostMapping(path = "/regions/{regionId}/countries")
    public ResponseEntity<CountryRestResponse> createCountry(@RequestBody CountryDetailRequestModel countryDetail, @PathVariable Long regionId) {
        Country country = modelMapper.map(countryDetail, Country.class);

        Country createdCountry = countryService.createCountry(country, regionId);

        CountryRestResponse responseBody = modelMapper.map(createdCountry, CountryRestResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(path = "/countries")
    public ResponseEntity<List<CountryRestResponse>> getCountries(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<Country> countries = countryService.getCountries(page, limit);

        List<CountryRestResponse> countryResponseList = new ArrayList<>();
        countries.forEach(country -> countryResponseList.add(modelMapper.map(country, CountryRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(countryResponseList);
    }

    @GetMapping(path = "/regions/{regionId}/countries")
    public ResponseEntity<List<CountryRestResponse>> getCountriesByRegion(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @PathVariable Long regionId) {

        List<Country> countries = countryService.getCountriesByRegion(page, limit, regionId);

        List<CountryRestResponse> countryResponseList = new ArrayList<>();
        countries.forEach(country -> countryResponseList.add(modelMapper.map(country, CountryRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(countryResponseList);
    }

    @GetMapping(path = "/countries/{id}")
    public ResponseEntity<CountryRestResponse> getCountry(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);

        CountryRestResponse responseBody = modelMapper.map(country, CountryRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/countries/{id}")
    public ResponseEntity<CountryRestResponse> updateCountry(@PathVariable Long id, @RequestBody CountryDetailRequestModel countryDetails) {
        Country country = modelMapper.map(countryDetails, Country.class);

        Country updatedCountry = countryService.updateCountry(id, country);

        CountryRestResponse responseBody = modelMapper.map(updatedCountry, CountryRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(path = "/countries/{id}")
    public ResponseEntity<OperationStatusModel> deleteCountry(@PathVariable Long id) {
        OperationStatusModel responseBody = new OperationStatusModel();
        responseBody.setOperationName("DELETE");
        countryService.deleteCountry(id);
        responseBody.setOperationResult("SUCCESS");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
