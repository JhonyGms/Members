package com.asamblea.api.reactive.apirestreactivemongo.functional;

import com.asamblea.api.reactive.apirestreactivemongo.controllers.dto.MembersDto;
import com.asamblea.api.reactive.apirestreactivemongo.documents.Members;
import com.asamblea.api.reactive.apirestreactivemongo.repository.MembersPsRepository;
import com.asamblea.api.reactive.apirestreactivemongo.repository.MembersRepository;
import com.asamblea.api.reactive.apirestreactivemongo.repository.entity.MembersEntity;
import com.asamblea.api.reactive.apirestreactivemongo.services.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class MembersHandler {

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private MembersPsRepository membersPsRepository;

    private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    public Mono<ServerResponse> listMembers(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(membersRepository.findAll(), Members.class);
    }

    public Mono<ServerResponse> getMembersForId(ServerRequest request){
        String id = request.pathVariable("id");

        return membersRepository.findById(id)
                .flatMap(members ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(members)))
                .switchIfEmpty(response404);
    }

    public Mono<ServerResponse> getMembersForCodeBar(ServerRequest request){
        String codeBar = request.pathVariable("codeBar");

        return membersRepository.findFirstByCodeBar(codeBar)
                .flatMap(members ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(members)))
                .switchIfEmpty(response404);
    }

    public Mono<ServerResponse> insertMembers(ServerRequest request){
        Mono<Members> membersMono = request.bodyToMono(Members.class);

        return membersMono
                .flatMap(members -> membersRepository.save(members)
                        .flatMap(members1 -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(members1))))
                .switchIfEmpty(response406);
    }

    public Mono<ServerResponse> updateMembers(ServerRequest request){
        Mono<Members> membersMono = request.bodyToMono(Members.class);
        String id = request.pathVariable("id");

        Mono<Members> update = membersMono
                .flatMap(members ->
                        membersRepository.findById(id)
                                .flatMap(oldMember -> {
                                    oldMember.setName(members.getName());
                                    oldMember.setApart(members.getApart());
                                    oldMember.setTower(members.getTower());
                                    oldMember.setCoefficient(members.getCoefficient());
                                    oldMember.setCodeBar(members.getCodeBar());
                                    oldMember.setCanVote(members.isCanVote());
                                    oldMember.setNameSecond(members.getNameSecond());

                                    return membersRepository.save(oldMember);
                                }));
        return update
                .flatMap(members ->
                        ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(members)))
                .switchIfEmpty(response404);
    }

    public Mono<ServerResponse> deleteMembers(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Void> delete = membersRepository.deleteById(id);

        return  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(delete,Void.class);
    }

    public Mono<ServerResponse> listMembersPs(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(membersPsRepository.findAll(), Members.class);
    }

    public Mono<ServerResponse> insertMembersPs(ServerRequest request){
        Mono<MembersDto> membersMono = request.bodyToMono(MembersDto.class);

        return membersMono.flatMap(
                        membersDto -> membersPsRepository.save(MembersEntity.builder()
                                        .id(membersDto.id())
                                        .name(membersDto.name())
                                        .nameSecond(membersDto.nameSecond())
                                        .tower(membersDto.tower())
                                        .apart(membersDto.apart())
                                        .coefficient(membersDto.coefficient())
                                        .canVote(membersDto.canVote())
                                        .codeBar(membersDto.codeBar())
                                        .build())
                                .flatMap(membersEntity -> ServerResponse.accepted()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(fromValue(membersEntity))))
                .switchIfEmpty(response406);
    }

}





