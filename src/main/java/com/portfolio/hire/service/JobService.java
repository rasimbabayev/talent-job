package com.portfolio.hire.service;

import com.portfolio.hire.dao.repository.JobRepository;
import com.portfolio.hire.exception.NotFoundException;
import com.portfolio.hire.model.response.GenericResponseDto;
import com.portfolio.hire.model.response.JobResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.portfolio.hire.mapper.JobResponseDtoBuilder.toJob;
import static com.portfolio.hire.model.constants.ExceptionConstants.JOB_NOT_FOUND_CODE;
import static com.portfolio.hire.model.constants.ExceptionConstants.JOB_NOT_FOUND_MESSAGE;
import static com.portfolio.hire.model.enums.Status.ACTIVE;
import static com.portfolio.hire.model.enums.Status.CANCELLED;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public GenericResponseDto<JobResponseDto> createJob(String id, Date date1, Date date2) {
        var job = toJob(id, date1, date2);

        return GenericResponseDto.<JobResponseDto>builder()
                .data(new JobResponseDto(jobRepository.save(job).getId()))
                .build();
    }

    public void cancelJob(String id) {
        var job = jobRepository.findByIdAndStatus(id, ACTIVE).orElseThrow(
                () -> new NotFoundException(
                        String.format(JOB_NOT_FOUND_MESSAGE, id), JOB_NOT_FOUND_CODE
                )
        );

        job.getShifts().forEach(shift -> shift.setStatus(CANCELLED));

        jobRepository.save(job.setStatus(CANCELLED));
    }
}
