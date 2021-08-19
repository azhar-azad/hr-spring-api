package com.azad.hrspringapi.ui.models.response;

public class DepartmentRestResponse {

    private Long id;
    private String departmentName;
    private String managerName;
    private LocationRestResponse location;

    public DepartmentRestResponse() {
    }

    public DepartmentRestResponse(Long id, String departmentName, String managerName, LocationRestResponse location) {
        this.id = id;
        this.departmentName = departmentName;
        this.managerName = managerName;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public LocationRestResponse getLocation() {
        return location;
    }

    public void setLocation(LocationRestResponse location) {
        this.location = location;
    }
}
