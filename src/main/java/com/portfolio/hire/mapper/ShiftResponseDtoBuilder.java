package com.portfolio.hire.mapper;

import com.portfolio.hire.dao.entity.Shift;
import com.portfolio.hire.model.response.ShiftResponseDto;

public class ShiftResponseDtoBuilder {

    public static ShiftResponseDto toShiftResponse(String id, Shift shift) {
        return ShiftResponseDto.builder()
                .id(id)
                .talentId(shift.getTalent() != null ? shift.getTalent().getId() : null)
                .jobId(shift.getJob().getId())
                .start(shift.getCreatedAt())
                .end(shift.getEndTime())
                .build();
    }
}
