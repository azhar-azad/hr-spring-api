package com.azad.hrspringapi.io.repositories;

import com.azad.hrspringapi.io.entities.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
}
