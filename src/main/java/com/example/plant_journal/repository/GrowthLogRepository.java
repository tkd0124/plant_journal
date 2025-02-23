package com.example.plant_journal.repository;

import com.example.plant_journal.model.GrowthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowthLogRepository extends JpaRepository<GrowthLog, Long> {
}
