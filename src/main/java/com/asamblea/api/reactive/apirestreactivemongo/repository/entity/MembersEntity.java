package com.asamblea.api.reactive.apirestreactivemongo.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("members")
public class MembersEntity {

    @Id
    private Integer id;
    @Column("name")
    private String name;
    @Column("nameSecond")
    private String nameSecond;
    @Column("tower")
    private String tower;
    @Column("apart")
    private String apart;
    @Column("coefficient")
    private float coefficient;
    @Column("canVote")
    private boolean canVote;
    @Column("codeBar")
    private String codeBar;
}
