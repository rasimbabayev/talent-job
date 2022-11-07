package com.portfolio.hire.controller;

import com.portfolio.hire.model.request.TalentRequestDto;
import com.portfolio.hire.model.response.GenericResponseDto;
import com.portfolio.hire.model.response.ShiftResponseDto;
import com.portfolio.hire.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/shift")
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public GenericResponseDto<List<ShiftResponseDto>> getShifts(@RequestParam String jobId) {
        return shiftService.getShifts(jobId);
    }

    @PatchMapping(path = "/{id}/book")
    @ResponseStatus(code = NO_CONTENT)
    public void bookTalent(@PathVariable("id") String shiftId, @RequestBody @Valid TalentRequestDto request) {
        shiftService.bookTalent(shiftId, request.getTalentId());
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void cancelShift(@PathVariable("id") String shiftId) {
        shiftService.cancelShift(shiftId);
    }

    @DeleteMapping(path = "/talent/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void cancelTalentAllShifts(@PathVariable("id") String talentId) {
        shiftService.cancelTalentAllShifts(talentId);
    }
}
