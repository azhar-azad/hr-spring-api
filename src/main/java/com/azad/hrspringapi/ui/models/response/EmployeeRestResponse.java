package com.azad.hrspringapi.ui.models.response;

import java.util.Date;

public class EmployeeRestResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private double salary;
    private DepartmentRestResponse department;
    private JobRestResponse job;

    public EmployeeRestResponse() {
    }

    public EmployeeRestResponse(Long id, String firstName, String lastName, String email, String phoneNumber, Date hireDate, double salary, DepartmentRestResponse department, JobRestResponse job) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.department = department;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public DepartmentRestResponse getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentRestResponse department) {
        this.department = department;
    }

    public JobRestResponse getJob() {
        return job;
    }

    public void setJob(JobRestResponse job) {
        this.job = job;
    }
}
