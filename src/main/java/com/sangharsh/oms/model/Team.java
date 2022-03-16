package com.sangharsh.oms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "team")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Team extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

}
