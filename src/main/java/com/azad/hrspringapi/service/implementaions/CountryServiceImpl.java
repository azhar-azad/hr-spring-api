package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.CountryServiceException;
import com.azad.hrspringapi.io.entities.Country;
import com.azad.hrspringapi.io.entities.Region;
import com.azad.hrspringapi.io.repositories.CountryRepository;
import com.azad.hrspringapi.service.CountryService;
import com.azad.hrspringapi.service.RegionService;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private RegionService regionService;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, RegionService regionService) {
        this.countryRepository = countryRepository;
        this.regionService = regionService;
    }

    @Override
    public Country createCountry(Country country, Long regionId) {
        Region region = regionService.getRegionById(regionId);
        if (region == null) {
            throw new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + regionId);
        }

        country.setRegion(region);

        return countryRepository.save(country);
    }

    @Override
    public List<Country> getCountries(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("countryName"));
        List<Country> countries = countryRepository.findAll(pageable).getContent();

        if (countries.size() == 0) {
            throw  new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());
        }

        return countries;
    }

    @Override
    public List<Country> getCountriesByRegion(int page, int limit, Long regionId) {
        Region region = regionService.getRegionById(regionId);
        if (region == null) {
            throw new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + regionId);
        }

        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("countryName"));
        List<Country> countries = countryRepository.findAllByRegion(region, pageable).getContent();

        if (countries.size() == 0) {
            throw  new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());
        }

        return countries;
    }

    @Override
    public Country getCountryById(Long id) {
        Country country = countryRepository.findById(id).orElse(null);

        if (country == null)
            throw new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        return country;
    }

    @Override
    public Country updateCountry(Long id, Country updatedCountryData) {
        if (updatedCountryData.getCountryName().equals("")) {
            throw new CountryServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        Country country = countryRepository.findById(id).orElse(null);

        if (country == null)
            throw new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        country.setCountryName(updatedCountryData.getCountryName());

        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id).orElse(null);

        if (country == null)
            throw new CountryServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        countryRepository.delete(country);
    }
}
