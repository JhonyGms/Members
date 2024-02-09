package com.asamblea.api.reactive.apirestreactivemongo.services;

import com.asamblea.api.reactive.apirestreactivemongo.controllers.dto.MembersDto;
import reactor.core.publisher.Mono;

public interface MembersService {
    Mono<MembersDto> createMembers(MembersDto membersDto);
}
