package com.cricket.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cricket.app.entity.Player;
import com.cricket.app.entity.Team;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam(Team team);
    List<Player> findByRole(String role);
    Optional<Player> findByPlayerName(String playerName);
}