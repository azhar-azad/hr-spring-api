package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.EmployeeServiceException;
import com.azad.hrspringapi.io.entities.Department;
import com.azad.hrspringapi.io.entities.Employee;
import com.azad.hrspringapi.io.entities.Job;
import com.azad.hrspringapi.io.repositories.EmployeeRepository;
import com.azad.hrspringapi.service.DepartmentService;
import com.azad.hrspringapi.service.EmployeeService;
import com.azad.hrspringapi.service.JobService;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private DepartmentService departmentService;
    private JobService jobService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentService departmentService, JobService jobService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.jobService = jobService;
    }

    @Override
    public Employee createEmployee(Employee employee, Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " DEPARTMENT ID: " + departmentId);
        }

        employee.setDepartment(department);;

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("hireDate"));
        List<Employee> employees = employeeRepository.findAll(pageable).getContent();

        if (employees.size() == 0) {
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_AVAILABLE.getErrorMessage());
        }

        return employees;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int page, int limit, Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " DEPARTMENT ID: " + departmentId);
        }

        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("hireDate"));
        List<Employee> employees = employeeRepository.findAllByDepartment(department, pageable).getContent();

        return employees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null)
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " EMPLOYEE ID: " + id);

        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployeeData) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null)
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " EMPLOYEE ID: " + id);

        String firstName = updatedEmployeeData.getFirstName();
        if (firstName != null && !firstName.equals(""))
            employee.setFirstName(firstName);
        String lastName = updatedEmployeeData.getLastName();
        if (lastName != null && !lastName.equals(""))
            employee.setLastName(lastName);
        String email = updatedEmployeeData.getEmail();
        if (email != null && !email.equals(""))
            employee.setEmail(email);
        String phoneNumber = updatedEmployeeData.getPhoneNumber();
        if (phoneNumber != null && !phoneNumber.equals(""))
            employee.setPhoneNumber(phoneNumber);
        Date hireDate = updatedEmployeeData.getHireDate();
        if (hireDate != null)
            employee.setHireDate(hireDate);
        double salary = updatedEmployeeData.getSalary();
        if (salary > 0)
            employee.setSalary(salary);

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null)
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " EMPLOYEE ID: " + id);

        employeeRepository.delete(employee);
    }

    @Override
    public Employee assignJob(Long employeeId, Long jobId) {
        Job job = jobService.getJobById(jobId);
        if (job == null)
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " JOB ID: " + jobId);

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null)
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " EMPLOYEE ID: " + employeeId);

        employee.setJob(job);

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByJob(int page, int limit, Long jobId) {
        Job job = jobService.getJobById(jobId);
        if (job == null) {
            throw new EmployeeServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " JOB ID: " + jobId);
        }

        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("hireDate"));
        List<Employee> employees = employeeRepository.findAllByJob(job, pageable).getContent();

        return employees;
    }
}
