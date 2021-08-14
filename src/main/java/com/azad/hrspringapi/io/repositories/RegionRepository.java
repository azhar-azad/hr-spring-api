package com.azad.hrspringapi.io.repositories;

import com.azad.hrspringapi.io.entities.Region;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegionRepository extends PagingAndSortingRepository<Region, Long> {
}
