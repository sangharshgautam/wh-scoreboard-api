package com.sangharsh.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Game extends BaseModel {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private List<TeamScore> teamScores;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private GameStatus status;
}
