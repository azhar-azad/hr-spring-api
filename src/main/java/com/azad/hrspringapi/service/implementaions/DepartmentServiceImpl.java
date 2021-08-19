package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.DepartmentServiceException;
import com.azad.hrspringapi.io.entities.Department;
import com.azad.hrspringapi.io.entities.Location;
import com.azad.hrspringapi.io.repositories.DepartmentRepository;
import com.azad.hrspringapi.service.DepartmentService;
import com.azad.hrspringapi.service.LocationService;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    private LocationService locationService;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, LocationService locationService) {
        this.departmentRepository = departmentRepository;
        this.locationService = locationService;
    }

    @Override
    public Department createDepartment(Department department, Long locationId) {
        Location location = locationService.getLocationById(locationId);
        if (location == null) {
            throw new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + locationId);
        }

        // check manager before saving department

        department.setLocation(location);

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        List<Department> departments = departmentRepository.findAll(pageable).getContent();

        if (departments.size() == 0) {
            throw  new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());
        }

        return departments;
    }

    @Override
    public List<Department> getDepartmentsByLocation(int page, int limit, Long locationId) {
        Location location = locationService.getLocationById(locationId);
        if (location == null) {
            throw new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + locationId);
        }

        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        List<Department> departments = departmentRepository.findAllByLocation(location, pageable).getContent();

        if (departments.size() == 0) {
            throw  new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());
        }

        return departments;
    }

    @Override
    public Department getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null)
            throw new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        return department;
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartmentData) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null)
            throw new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        // check manager before saving department

        if (updatedDepartmentData.getDepartmentName() != null && !updatedDepartmentData.getDepartmentName().equals(""))
            department.setDepartmentName(updatedDepartmentData.getDepartmentName());
        if (updatedDepartmentData.getManagerName() != null && !updatedDepartmentData.getManagerName().equals(""))
            department.setManagerName(updatedDepartmentData.getManagerName());

        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElse(null);

        if (department == null)
            throw new DepartmentServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        departmentRepository.delete(department);
    }
}
