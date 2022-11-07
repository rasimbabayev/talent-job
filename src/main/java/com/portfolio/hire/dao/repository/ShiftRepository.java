package com.portfolio.hire.dao.repository;

import com.portfolio.hire.dao.entity.Shift;
import com.portfolio.hire.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, String> {

    List<Shift> findAllByJobIdAndStatus(String jobId, Status status);

    List<Shift> findAllByTalentIdAndStatus(String talentId, Status status);

    Optional<Shift> findByIdAndStatus(String id, Status status);
}
