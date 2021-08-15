package com.azad.hrspringapi.service;

import com.azad.hrspringapi.io.entities.Country;

import java.util.List;

public interface CountryService {

    Country createCountry(Country country, Long regionId);

    List<Country> getCountries(int page, int limit);

    List<Country> getCountriesByRegion(int page, int limit, Long regionId);

    Country getCountryById(Long id);

    Country updateCountry(Long id, Country country);

    void deleteCountry(Long id);
}
