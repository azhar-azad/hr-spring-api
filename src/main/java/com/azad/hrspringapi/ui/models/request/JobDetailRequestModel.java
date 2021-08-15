package com.azad.hrspringapi.ui.models.request;

public class JobDetailRequestModel {

    private String jobTitle;
    private double minSalary;
    private double maxSalary;

    public JobDetailRequestModel() {
    }

    public JobDetailRequestModel(String jobTitle, double minSalary, double maxSalary) {
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }
}
