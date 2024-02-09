package com.asamblea.api.reactive.apirestreactivemongo.services.impl;

import com.asamblea.api.reactive.apirestreactivemongo.controllers.dto.MembersDto;
import com.asamblea.api.reactive.apirestreactivemongo.repository.MembersPsRepository;
import com.asamblea.api.reactive.apirestreactivemongo.repository.entity.MembersEntity;
import com.asamblea.api.reactive.apirestreactivemongo.services.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class MembersServiceimpl implements MembersService {

    private  final MembersPsRepository membersPsRepository;

    @Override
    public Mono<MembersDto> createMembers(MembersDto membersDto){
        return membersPsRepository.save(MembersEntity.builder()
                .id(membersDto.id())
                .name(membersDto.name())
                .nameSecond(membersDto.nameSecond())
                .tower(membersDto.tower())
                .apart(membersDto.apart())
                .coefficient(membersDto.coefficient())
                .canVote(membersDto.canVote())
                .codeBar(membersDto.codeBar())
                .build())
                .map(membersEntity -> new MembersDto(membersEntity.getId(), membersEntity.getName(), membersEntity.getNameSecond(), membersEntity.getTower(), membersEntity.getApart(), membersEntity.getCoefficient(), membersEntity.isCanVote(), membersEntity.getCodeBar()));
    }

}
