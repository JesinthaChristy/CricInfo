package com.cricket.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "batting_scores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattingScore {
    
    public Long getBattingScoreId() {
		return battingScoreId;
	}

	public void setBattingScoreId(Long battingScoreId) {
		this.battingScoreId = battingScoreId;
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

	public Integer getRunsScored() {
		return runsScored;
	}

	public void setRunsScored(Integer runsScored) {
		this.runsScored = runsScored;
	}

	public Integer getBallsFaced() {
		return ballsFaced;
	}

	public void setBallsFaced(Integer ballsFaced) {
		this.ballsFaced = ballsFaced;
	}

	public Integer getFours() {
		return fours;
	}

	public void setFours(Integer fours) {
		this.fours = fours;
	}

	public Integer getSixes() {
		return sixes;
	}

	public void setSixes(Integer sixes) {
		this.sixes = sixes;
	}

	public Double getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(Double strikeRate) {
		this.strikeRate = strikeRate;
	}

	public String getDismissalType() {
		return dismissalType;
	}

	public void setDismissalType(String dismissalType) {
		this.dismissalType = dismissalType;
	}

	public Player getBowler() {
		return bowler;
	}

	public void setBowler(Player bowler) {
		this.bowler = bowler;
	}

	public Player getFielder() {
		return fielder;
	}

	public void setFielder(Player fielder) {
		this.fielder = fielder;
	}

	public Integer getBattingPosition() {
		return battingPosition;
	}

	public void setBattingPosition(Integer battingPosition) {
		this.battingPosition = battingPosition;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long battingScoreId;
    
    @ManyToOne
    @JoinColumn(name = "innings_id", nullable = false)
    private Innings innings;
    
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    
    private Integer runsScored;
    private Integer ballsFaced;
    private Integer fours;
    private Integer sixes;
    private Double strikeRate;
    private String dismissalType;
    
    @ManyToOne
    @JoinColumn(name = "bowler_id")
    private Player bowler;
    
    @ManyToOne
    @JoinColumn(name = "fielder_id")
    private Player fielder;
    
    private Integer battingPosition;
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        calculateStrikeRate();
    }
    
    private void calculateStrikeRate() {
        if (ballsFaced != null && ballsFaced > 0) {
            strikeRate = (runsScored * 100.0) / ballsFaced;
        }
    }
}