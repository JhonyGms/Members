package com.asamblea.api.reactive.apirestreactivemongo.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "members")
@Data
public class Members {

    @Id
    private Integer id;
    private String name;
    private String nameSecond;
    private String tower;
    private String apart;
    private double coefficient;
    private boolean canVote;
    private String codeBar;

}
