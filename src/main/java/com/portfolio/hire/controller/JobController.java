package com.portfolio.hire.controller;

import com.portfolio.hire.model.request.JobRequestDto;
import com.portfolio.hire.model.response.GenericResponseDto;
import com.portfolio.hire.model.response.JobResponseDto;
import com.portfolio.hire.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/job")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public GenericResponseDto<JobResponseDto> requestJob(@RequestBody JobRequestDto requestDto) {
        return jobService.createJob(requestDto.getCompanyId(), requestDto.getStart(), requestDto.getEnd());
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void cancelJob(@PathVariable("id") String jobId) {
        jobService.cancelJob(jobId);
    }
}
