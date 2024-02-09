package com.asamblea.api.reactive.apirestreactivemongo.repository;

import com.asamblea.api.reactive.apirestreactivemongo.documents.Members;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MembersRepository extends ReactiveMongoRepository<Members, String> {

    Mono<Members> findFirstByCodeBar(String codeBar);

}
