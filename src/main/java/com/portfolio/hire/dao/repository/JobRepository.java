package com.portfolio.hire.dao.repository;

import com.portfolio.hire.dao.entity.Job;
import com.portfolio.hire.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    Optional<Job> findByIdAndStatus(String id, Status status);

}
