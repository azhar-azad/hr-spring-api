package com.azad.hrspringapi.io.repositories;

import com.azad.hrspringapi.io.entities.Department;
import com.azad.hrspringapi.io.entities.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    Page<Department> findAllByLocation(Location location, Pageable pageable);
}
