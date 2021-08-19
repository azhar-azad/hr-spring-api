package com.azad.hrspringapi.ui.models.request;

public class DepartmentDetailRequestModel {

    private String departmentName;
    private String managerName;

    public DepartmentDetailRequestModel() {
    }

    public DepartmentDetailRequestModel(String departmentName, String managerName) {
        this.departmentName = departmentName;
        this.managerName = managerName;
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
}
