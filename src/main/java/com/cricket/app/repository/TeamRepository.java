package com.cricket.app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cricket.app.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);
    Optional<Team> findByTeamCode(String teamCode);
}