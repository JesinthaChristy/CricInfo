package com.cricket.app.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScorecardDTO {
    private Long matchId;
    private MatchDTO matchDetails;
    private List<InningsDTO> innings;
}