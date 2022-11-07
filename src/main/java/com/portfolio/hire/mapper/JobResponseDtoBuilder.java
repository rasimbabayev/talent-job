package com.portfolio.hire.mapper;

import com.portfolio.hire.dao.entity.Job;
import com.portfolio.hire.dao.entity.Shift;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.portfolio.hire.model.enums.Status.ACTIVE;

public class JobResponseDtoBuilder {

    public static Job toJob(String companyId, Date date1, Date date2) {
        var job = Job.builder()
                .id(UUID.randomUUID().toString())
                .companyId(companyId)
                .status(ACTIVE)
                .startTime(date1.toInstant().plus(8,ChronoUnit.HOURS))
                .endTime(date2.toInstant().plus(17, ChronoUnit.HOURS))
                .build();

        job.setShifts(LongStream.range(0, ChronoUnit.DAYS.between(date1.toInstant(), date2.toInstant()))
                .mapToObj(idx -> date1.toInstant().plus(idx, ChronoUnit.DAYS))
                .map(date -> Shift.builder()
                        .id(UUID.randomUUID().toString())
                        .job(job)
                        .status(ACTIVE)
                        .startTime(date1.toInstant().plus(8,ChronoUnit.HOURS))
                        .endTime(date2.toInstant().plus(17, ChronoUnit.HOURS))
                        .build())
                .collect(Collectors.toList()));

        return job;
    }
}
