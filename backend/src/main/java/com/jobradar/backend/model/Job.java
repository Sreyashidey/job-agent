package com.jobradar.backend.model;

public class Job {
    private Long id;
    private String company;
    private String title;
    private String location;
    private String experience;

    public Job(Long id,String company,String title, String location, String experience){
        this.id=id;
        this.company=company;
        this.title=title;
        this.location=location;
        this.experience=experience;
    }

    public Long getId(){
        return id;
    }
    public String getCompany(){
        return company;
    }

    public String getTitle(){
        return title;
    }
    
    public String getLocation(){
        return location;
    }

    public String getExperience(){
        return experience;
    }
    
}
