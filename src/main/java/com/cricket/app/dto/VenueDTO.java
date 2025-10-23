package com.cricket.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueDTO {
    private Long venueId;
    private String venueName;
    private String city;
    private String country;
    private Integer capacity;
}