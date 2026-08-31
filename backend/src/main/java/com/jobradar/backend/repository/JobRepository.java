package com.jobradar.backend.repository;

import com.jobradar.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyIgnoreCase(String company);
    List<Job> findByLocationIgnoreCase(String location);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByExperienceIgnoreCase(String experience);
}
