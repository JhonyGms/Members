package com.asamblea.api.reactive.apirestreactivemongo.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import  static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class MembersRouter {

    @Bean
    public RouterFunction<ServerResponse>  routeMember( MembersHandler membersHandler){
        return RouterFunctions
                .route(GET("/functional/members"), membersHandler::listMembers)
                .andRoute(GET("/functional/members/{id}"),membersHandler::getMembersForId)
                .andRoute(GET("/functional/members/byCodeBar/{codeBar}"),membersHandler::getMembersForCodeBar)
                .andRoute(POST("/functional/members"),membersHandler::insertMembers)
                .andRoute(POST("/functional/membersPs"),membersHandler::insertMembersPs)
                .andRoute(PUT("/functional/members/{id}"),membersHandler::updateMembers)
                .andRoute(DELETE("/functional/members/{id}"),membersHandler::deleteMembers);

    }
}
