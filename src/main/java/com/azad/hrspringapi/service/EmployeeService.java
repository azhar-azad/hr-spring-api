package com.azad.hrspringapi.service;

import com.azad.hrspringapi.io.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee, Long departmentId);

    List<Employee> getEmployees(int page, int limit);

    List<Employee> getEmployeesByDepartment(int page, int limit, Long departmentId);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee updatedEmployeeData);

    void deleteEmployee(Long id);

    Employee assignJob(Long employeeId, Long jobId);

    List<Employee> getEmployeesByJob(int page, int limit, Long jobId);
}
