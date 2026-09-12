package com.jobradar.backend.dto;

public class JobDTO {

    private String company;
    private String title;
    private String location;
    private String experience;
    private String source;
    private String externalJobId;
    private String url;

    public JobDTO() {
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

    public void setCompany(String company) {
        this.company = company;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getSource() {
    return source;
}

public void setSource(String source) {
    this.source = source;
}

public String getExternalJobId() {
    return externalJobId;
}

public void setExternalJobId(String externalJobId) {
    this.externalJobId = externalJobId;
}

public String getUrl() {
    return url;
}

public void setUrl(String url) {
    this.url = url;
}
}