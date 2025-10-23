package com.cricket.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "innings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Innings {
    
    public Long getInningsId() {
		return inningsId;
	}

	public void setInningsId(Long inningsId) {
		this.inningsId = inningsId;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Team getBattingTeam() {
		return battingTeam;
	}

	public void setBattingTeam(Team battingTeam) {
		this.battingTeam = battingTeam;
	}

	public Team getBowlingTeam() {
		return bowlingTeam;
	}

	public void setBowlingTeam(Team bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}

	public Integer getInningsNumber() {
		return inningsNumber;
	}

	public void setInningsNumber(Integer inningsNumber) {
		this.inningsNumber = inningsNumber;
	}

	public Integer getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(Integer totalRuns) {
		this.totalRuns = totalRuns;
	}

	public Integer getTotalWickets() {
		return totalWickets;
	}

	public void setTotalWickets(Integer totalWickets) {
		this.totalWickets = totalWickets;
	}

	public Double getTotalOvers() {
		return totalOvers;
	}

	public void setTotalOvers(Double totalOvers) {
		this.totalOvers = totalOvers;
	}

	public Integer getExtras() {
		return extras;
	}

	public void setExtras(Integer extras) {
		this.extras = extras;
	}

	public Boolean getDeclared() {
		return declared;
	}

	public void setDeclared(Boolean declared) {
		this.declared = declared;
	}

	public List<BattingScore> getBattingScores() {
		return battingScores;
	}

	public void setBattingScores(List<BattingScore> battingScores) {
		this.battingScores = battingScores;
	}

	public List<BowlingScore> getBowlingScores() {
		return bowlingScores;
	}

	public void setBowlingScores(List<BowlingScore> bowlingScores) {
		this.bowlingScores = bowlingScores;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inningsId;
    
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    @ManyToOne
    @JoinColumn(name = "batting_team_id", nullable = false)
    private Team battingTeam;
    
    @ManyToOne
    @JoinColumn(name = "bowling_team_id", nullable = false)
    private Team bowlingTeam;
    
    private Integer inningsNumber;
    private Integer totalRuns;
    private Integer totalWickets;
    private Double totalOvers;
    private Integer extras;
    private Boolean declared;
    
    @OneToMany(mappedBy = "innings", cascade = CascadeType.ALL)
    private List<BattingScore> battingScores;
    
    @OneToMany(mappedBy = "innings", cascade = CascadeType.ALL)
    private List<BowlingScore> bowlingScores;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}