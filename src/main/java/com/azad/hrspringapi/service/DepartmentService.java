package com.azad.hrspringapi.service;

import com.azad.hrspringapi.io.entities.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department, Long locationId);

    List<Department> getDepartments(int page, int limit);

    List<Department> getDepartmentsByLocation(int page, int limit, Long locationId);

    Department getDepartmentById(Long id);

    Department updateDepartment(Long id, Department updatedDepartmentData);

    void deleteDepartment(Long id);
}
