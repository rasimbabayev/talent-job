package com.portfolio.hire.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.hire.model.request.JobRequestDto;
import com.portfolio.hire.service.JobService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
@RunWith(MockitoJUnitRunner.class)
public class JobControllerTests {

    private MockMvc mockMvc;

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    @Test
    public void shouldCreateAJob() throws Exception {

        String json = new ObjectMapper().writeValueAsString(
                new JobRequestDto("1", new Date(), new Date())
        );

        mockMvc.perform(post("/job")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCancelJob() throws Exception {

        mockMvc.perform(delete("/job/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}

