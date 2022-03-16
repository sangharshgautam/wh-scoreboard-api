package com.sangharsh.oms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@NoArgsConstructor
public class Auditable implements Serializable {
    @CreatedBy
    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = true)
    @JsonIgnore
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "modified_date")
    @JsonIgnore
    private LocalDateTime modifiedAt;

}
