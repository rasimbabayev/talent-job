package com.portfolio.hire.mapper;

import com.portfolio.hire.dao.entity.Talent;
import com.portfolio.hire.model.request.TalentCreateRequest;

import java.util.UUID;

public class TalentBuilder {
    public static Talent toTalent(TalentCreateRequest request) {
        return Talent.builder()
                .id(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .build();
    }
}
