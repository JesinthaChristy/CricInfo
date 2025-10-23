package com.cricket.app.repository;

import com.cricket.app.entity.BowlingScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BowlingScoreRepository extends JpaRepository<BowlingScore, Long> {
    List<BowlingScore> findByInningsInningsId(Long inningsId);
}