package com.azad.hrspringapi.io.entities;

public class Department {

    private Long id;
    private String departmentName;
    private String managerName;

    // mapping with Location

    // mapping with Employee

    // mapping with JobHistory

    public Department() {
    }

    public Department(String departmentName, String managerName) {
        this.departmentName = departmentName;
        this.managerName = managerName;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
