package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.io.entities.Department;
import com.azad.hrspringapi.service.DepartmentService;
import com.azad.hrspringapi.ui.models.request.DepartmentDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.DepartmentRestResponse;
import com.azad.hrspringapi.ui.models.response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {

    private ModelMapper modelMapper = new ModelMapper();

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/departments/test")
    public String departmentRouterTest() {
        return "Department controller is up and running";
    }

    @PostMapping(path = "/locations/{locationId}/departments")
    public ResponseEntity<DepartmentRestResponse> createDepartment(@RequestBody DepartmentDetailRequestModel departmentDetail, @PathVariable Long locationId) {
        Department department = modelMapper.map(departmentDetail, Department.class);

        Department createdDepartment = departmentService.createDepartment(department, locationId);

        DepartmentRestResponse responseBody = modelMapper.map(createdDepartment, DepartmentRestResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping(path = "/departments")
    public ResponseEntity<List<DepartmentRestResponse>> getDepartments(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<Department> departments = departmentService.getDepartments(page, limit);

        List<DepartmentRestResponse> departmentResponseList = new ArrayList<>();
        departments.forEach(
                department -> departmentResponseList.add(modelMapper.map(department, DepartmentRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseList);
    }

    @GetMapping(path = "/locations/{locationId}/departments")
    public ResponseEntity<List<DepartmentRestResponse>> getDepartmentsByLocation(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @PathVariable Long locationId) {

        List<Department> departments = departmentService.getDepartmentsByLocation(page, limit, locationId);

        List<DepartmentRestResponse> departmentResponseList = new ArrayList<>();
        departments.forEach(
                department -> departmentResponseList.add(modelMapper.map(department, DepartmentRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(departmentResponseList);
    }

    @GetMapping(path = "/departments/{id}")
    public ResponseEntity<DepartmentRestResponse> getDepartment(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);

        DepartmentRestResponse responseBody = modelMapper.map(department, DepartmentRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/departments/{id}")
    public ResponseEntity<DepartmentRestResponse> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDetailRequestModel departmentDetails) {
        Department department = modelMapper.map(departmentDetails, Department.class);

        Department updatedDepartment = departmentService.updateDepartment(id, department);

        DepartmentRestResponse responseBody = modelMapper.map(updatedDepartment, DepartmentRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(path = "/departments/{id}")
    public ResponseEntity<OperationStatusModel> deleteDepartment(@PathVariable Long id) {
        OperationStatusModel responseBody = new OperationStatusModel();
        responseBody.setOperationName("DELETE");
        departmentService.deleteDepartment(id);
        responseBody.setOperationResult("SUCCESS");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
