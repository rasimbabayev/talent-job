package com.portfolio.hire.service;


import com.portfolio.hire.dao.entity.Job;
import com.portfolio.hire.dao.repository.JobRepository;
import com.portfolio.hire.exception.NotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.portfolio.hire.model.enums.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class JobServiceTests {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository jobRepository;

    @Test
    public void checkIfFindByIdAndStatusDoesntFindThrowsNotFoundException() {
        when(jobRepository.findByIdAndStatus(anyString(), eq(ACTIVE))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> jobService.cancelJob(UUID.randomUUID().toString()));
        verify(jobRepository, Mockito.times(1)).findByIdAndStatus(anyString(), eq(ACTIVE));
    }

    @Test
    public void checkIfFindByIdAndStatusDoesntThrowNotFoundException() {

        var job = Job.builder().id(UUID.randomUUID().toString()).build();

        when(jobRepository.findByIdAndStatus(anyString(), eq(ACTIVE))).thenReturn(Optional.of(job));
        assertDoesNotThrow(() -> jobService.cancelJob(UUID.randomUUID().toString()));
    }

    @Test
    public void checkIfCreateJobReallyCreates() {
        var job = Job.builder().id("1").build();
        when(jobRepository.save(Mockito.any(Job.class))).thenReturn(job);
        assertEquals("1", jobService.createJob("1", new Date(), new Date()).getData().getJobId());
    }

}
