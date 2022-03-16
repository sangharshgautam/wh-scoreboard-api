package com.sangharsh.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "team_score")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class TeamScore extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "GAME_ID", updatable = false)
    @JsonIgnore
    private Game game;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name="SCORE")
    private int score;

}
