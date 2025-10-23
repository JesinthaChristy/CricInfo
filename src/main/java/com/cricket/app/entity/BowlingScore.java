package com.cricket.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bowling_scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BowlingScore {
    
    public Long getBowlingScoreId() {
		return bowlingScoreId;
	}

	public void setBowlingScoreId(Long bowlingScoreId) {
		this.bowlingScoreId = bowlingScoreId;
	}

	public Innings getInnings() {
		return innings;
	}

	public void setInnings(Innings innings) {
		this.innings = innings;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Double getOversBowled() {
		return oversBowled;
	}

	public void setOversBowled(Double oversBowled) {
		this.oversBowled = oversBowled;
	}

	public Integer getMaidens() {
		return maidens;
	}

	public void setMaidens(Integer maidens) {
		this.maidens = maidens;
	}

	public Integer getRunsConceded() {
		return runsConceded;
	}

	public void setRunsConceded(Integer runsConceded) {
		this.runsConceded = runsConceded;
	}

	public Integer getWicketsTaken() {
		return wicketsTaken;
	}

	public void setWicketsTaken(Integer wicketsTaken) {
		this.wicketsTaken = wicketsTaken;
	}

	public Double getEconomyRate() {
		return economyRate;
	}

	public void setEconomyRate(Double economyRate) {
		this.economyRate = economyRate;
	}

	public Integer getWides() {
		return wides;
	}

	public void setWides(Integer wides) {
		this.wides = wides;
	}

	public Integer getNoBalls() {
		return noBalls;
	}

	public void setNoBalls(Integer noBalls) {
		this.noBalls = noBalls;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bowlingScoreId;
    
    @ManyToOne
    @JoinColumn(name = "innings_id", nullable = false)
    private Innings innings;
    
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    
    private Double oversBowled;
    private Integer maidens;
    private Integer runsConceded;
    private Integer wicketsTaken;
    private Double economyRate;
    private Integer wides;
    private Integer noBalls;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        calculateEconomyRate();
    }
    
    private void calculateEconomyRate() {
        if (oversBowled != null && oversBowled > 0) {
            economyRate = runsConceded / oversBowled;
        }
    }
}