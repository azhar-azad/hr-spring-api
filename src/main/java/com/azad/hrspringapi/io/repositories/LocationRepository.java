package com.azad.hrspringapi.io.repositories;

import com.azad.hrspringapi.io.entities.Country;
import com.azad.hrspringapi.io.entities.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

    Page<Location> findAllByCountry(Country country, Pageable pageable);
}
