package com.asamblea.api.reactive.apirestreactivemongo.repository;

import com.asamblea.api.reactive.apirestreactivemongo.repository.entity.MembersEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface MembersPsRepository extends R2dbcRepository<MembersEntity, Integer> {
}
