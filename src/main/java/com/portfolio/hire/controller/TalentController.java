package com.portfolio.hire.controller;

import com.portfolio.hire.model.request.TalentCreateRequest;
import com.portfolio.hire.model.response.GenericResponseDto;
import com.portfolio.hire.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/talent")
public class TalentController {

    private final TalentService talentService;

    @PostMapping
    public GenericResponseDto<String> createTalent(@RequestBody @Valid TalentCreateRequest request) {
        return talentService.createTalent(request);
    }
}
