package com.azad.hrspringapi.io.repositories;

import com.azad.hrspringapi.io.entities.Department;
import com.azad.hrspringapi.io.entities.Employee;
import com.azad.hrspringapi.io.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    Page<Employee> findAllByDepartment(Department department, Pageable pageable);

    Page<Employee> findAllByJob(Job job, Pageable pageable);
}
