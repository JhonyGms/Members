package com.asamblea.api.reactive.apirestreactivemongo.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "members")
@Data
public class Members {

    @Id
    private String id;
    private String name;
    private String nameSecond;
    private String tower;
    private String apart;
    private float coefficient;
    private boolean canVote;
    private String codeBar;

}
