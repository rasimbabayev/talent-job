package com.portfolio.hire.dao.repository;

import com.portfolio.hire.dao.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository extends JpaRepository<Talent, String> {
}
