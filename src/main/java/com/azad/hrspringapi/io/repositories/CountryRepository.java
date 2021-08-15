package com.azad.hrspringapi.io.repositories;

import com.azad.hrspringapi.io.entities.Country;
import com.azad.hrspringapi.io.entities.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

    Page<Country> findAllByRegion(Region region, Pageable pageable);
}
