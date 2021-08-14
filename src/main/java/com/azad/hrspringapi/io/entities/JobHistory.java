package com.azad.hrspringapi.io.entities;

import java.util.Date;

public class JobHistory {

    private Long id;
    private Date startDate;
    private Date endDate;

    // mapping with Employee

    // mapping with Department

    // mapping with Job


    public JobHistory() {
    }

    public JobHistory(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "JobHistory{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
