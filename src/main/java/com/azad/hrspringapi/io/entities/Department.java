package com.azad.hrspringapi.io.entities;

import javax.persistence.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String departmentName;

    private String managerName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
