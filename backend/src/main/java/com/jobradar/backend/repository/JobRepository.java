package com.jobradar.backend.repository;

import com.jobradar.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>,JpaSpecificationExecutor<Job> {
    List<Job> findByCompanyIgnoreCase(String company);
    List<Job> findByLocationIgnoreCase(String location);
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByExperienceIgnoreCase(String experience);
    boolean existsBySourceAndExternalJobId(String source,String externalJobId);
    Page<Job> findAll(Pageable pageable);
}
