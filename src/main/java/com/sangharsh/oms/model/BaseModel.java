package com.sangharsh.oms.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@NoArgsConstructor
@SuperBuilder
public abstract class BaseModel extends Auditable implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @Setter
    private boolean deleted = false;

}
