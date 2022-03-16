package com.sangharsh.oms.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application-integration.properties")
public class TeamScoreControllerIT {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test_401_failure() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/teamscore")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "YourUsername", password = "YourPassword", roles = "USER")
    public void testAll_success() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/teamscore")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(6)))
                .andExpect(jsonPath("$[0].team.name", Is.is("TEAM A")));
    }

    @Test
    @WithMockUser(username = "YourUsername", password = "YourPassword", roles = "USER")
    public void testGet_success() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/teamscore/7b4fa755-0179-4bed-b0ef-6e5c99803b0e")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.team.name", Is.is("TEAM A")))
                .andExpect(jsonPath("$.score", Is.is(1)));
    }
}
