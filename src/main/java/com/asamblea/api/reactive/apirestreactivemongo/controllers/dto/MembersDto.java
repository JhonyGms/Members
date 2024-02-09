package com.asamblea.api.reactive.apirestreactivemongo.controllers.dto;

public record MembersDto(Integer id,
         String name,
         String nameSecond,
         String tower,
         String apart,
         float coefficient,
         boolean canVote,
         String codeBar) {
}
