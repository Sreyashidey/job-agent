package com.jobradar.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String title;
    private String location;
    private String experience;

    public Job() {
    }

    public Job(Long id, String company, String title, String location, String experience) {
        this.id = id;
        this.company = company;
        this.title = title;
        this.location = location;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getExperience() {
        return experience;
    }
}