package com.jobradar.backend.specification;

import com.jobradar.backend.model.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> hasCompany(String company) {
        return (root, query, criteriaBuilder) ->
                company == null ? null :
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("company")),
                        company.toLowerCase()
                );
    }

    public static Specification<Job> hasLocation(String location) {
        return (root, query, criteriaBuilder) ->
                location == null ? null :
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("location")),
                        location.toLowerCase()
                );
    }

    public static Specification<Job> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ? null :
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Job> hasExperience(String experience) {
        return (root, query, criteriaBuilder) ->
                experience == null ? null :
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("experience")),
                        experience.toLowerCase()
                );
    }
}