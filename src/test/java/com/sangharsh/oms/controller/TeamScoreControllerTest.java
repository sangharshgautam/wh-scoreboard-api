package com.sangharsh.oms.controller;

import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.model.TeamScore;
import com.sangharsh.oms.repository.TeamScoreRepository;
import com.sangharsh.oms.service.TeamScoreService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application-integration.properties")
public class TeamScoreControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TeamScoreService teamScoreService;

    @MockBean
    private TeamScoreRepository teamScoreRepository;


    @Test
    @WithMockUser(username = "YourUsername", password = "YourPassword", roles = "USER")
    public void should_get_all_success() throws Exception {
        List<TeamScore> result = new ArrayList<>();
        result.add(TeamScore.builder().team(Team.builder().name("Test Team").build()).score(2).build());
        Mockito.when(teamScoreRepository.findAll()).thenReturn(result);
        mvc.perform(MockMvcRequestBuilders.get("/api/teamscore")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[0].team.name", Is.is("Test Team")))
                .andExpect(jsonPath("$[0].score", Is.is(2)));
        Mockito.verify(teamScoreRepository, Mockito.times(1)).findAll();
    }

    @Test
    @WithMockUser(username = "YourUsername", password = "YourPassword", roles = "USER")
    public void should_return_404_when_entity_not_found() throws Exception {
        Mockito.when(teamScoreRepository.getById("not-found-id")).thenThrow(new EntityNotFoundException("Entity not found"));
        mvc.perform(MockMvcRequestBuilders.get("/api/teamscore/not-found-id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        Mockito.verify(teamScoreRepository, Mockito.times(1)).findById("not-found-id");
    }

}
