package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.io.entities.Employee;
import com.azad.hrspringapi.service.EmployeeService;
import com.azad.hrspringapi.ui.models.request.EmployeeDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.EmployeeRestResponse;
import com.azad.hrspringapi.ui.models.response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeController {

    private ModelMapper modelMapper = new ModelMapper();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees/test")
    public String employeeRouterTest() {
        return "Employee controller is up and running";
    }

    @PostMapping(path = "/departments/{departmentId}/employees")
    public ResponseEntity<EmployeeRestResponse> createEmployee(@RequestBody EmployeeDetailRequestModel employeeDetails, @PathVariable Long departmentId) {
        String dateInString = employeeDetails.getHireDateString();
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = modelMapper.map(employeeDetails, Employee.class);
        employee.setHireDate(date);
        Employee createdEmployee = employeeService.createEmployee(employee, departmentId);
        EmployeeRestResponse responseBody = modelMapper.map(createdEmployee, EmployeeRestResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<EmployeeRestResponse>> getEmployees(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<Employee> employees = employeeService.getEmployees(page, limit);

        List<EmployeeRestResponse> employeeResponseList = new ArrayList<>();
        employees.forEach(
                employee -> employeeResponseList.add(modelMapper.map(employee, EmployeeRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseList);
    }

    @GetMapping(path = "/departments/{departmentId}/employees")
    public ResponseEntity<List<EmployeeRestResponse>> getEmployeesByDepartment(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @PathVariable Long departmentId) {

        List<Employee> employees = employeeService.getEmployeesByDepartment(page, limit, departmentId);

        List<EmployeeRestResponse> employeeResponseList = new ArrayList<>();
        employees.forEach(
                employee -> employeeResponseList.add(modelMapper.map(employee, EmployeeRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseList);
    }

    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<EmployeeRestResponse> getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);

        EmployeeRestResponse responseBody = modelMapper.map(employee, EmployeeRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<EmployeeRestResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDetailRequestModel employeeDetails) {
        String dateInString = employeeDetails.getHireDateString();
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = modelMapper.map(employeeDetails, Employee.class);
        employee.setHireDate(date);

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        EmployeeRestResponse responseBody = modelMapper.map(updatedEmployee, EmployeeRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<OperationStatusModel> deleteEmployee(@PathVariable Long id) {
        OperationStatusModel responseBody = new OperationStatusModel();
        responseBody.setOperationName("DELETE");
        employeeService.deleteEmployee(id);
        responseBody.setOperationResult("SUCCESS");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/employees/{id}/assign/{jobId}")
    public ResponseEntity<EmployeeRestResponse> assignJobToEmployee(@PathVariable Long id, @PathVariable Long jobId) {
        Employee employeeWithJob = employeeService.assignJob(id, jobId);

        EmployeeRestResponse responseBody = modelMapper.map(employeeWithJob, EmployeeRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @GetMapping(path = "/jobs/{jobId}/employees")
    public ResponseEntity<List<EmployeeRestResponse>> getEmployeesByJob(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @PathVariable Long jobId) {

        List<Employee> employees = employeeService.getEmployeesByJob(page, limit, jobId);

        List<EmployeeRestResponse> employeeResponseList = new ArrayList<>();
        employees.forEach(
                employee -> employeeResponseList.add(modelMapper.map(employee, EmployeeRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(employeeResponseList);
    }
}
