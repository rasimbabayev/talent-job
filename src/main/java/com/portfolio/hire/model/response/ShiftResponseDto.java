package com.portfolio.hire.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftResponseDto {
    private String id;
    private String talentId;
    private String jobId;
    private Instant start;
    private Instant end;
}
