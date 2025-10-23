package com.cricket.app.repository;

import com.cricket.app.entity.BattingScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BattingScoreRepository extends JpaRepository<BattingScore, Long> {
    List<BattingScore> findByInningsInningsId(Long inningsId);
}

