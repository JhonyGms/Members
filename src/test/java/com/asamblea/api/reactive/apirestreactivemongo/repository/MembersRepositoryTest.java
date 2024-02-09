package com.asamblea.api.reactive.apirestreactivemongo.repository;

import com.asamblea.api.reactive.apirestreactivemongo.documents.Members;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MembersRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private ReactiveMongoOperations mongoOperations;

    @BeforeAll
    public void insertDates() {
        Members member1 = new Members();
        member1.setId(1);
        member1.setName("c1@gmail.com");
        member1.setNameSecond("133222");
        member1.setTower("12");
        member1.setApart("133222");
        member1.setCoefficient(1.23);
        member1.setCanVote(true);
        member1.setCodeBar("133222");

        Members member2 = new Members();
        member2.setId(2);
        member2.setName("c1@gmail.com");
        member2.setNameSecond("133222");
        member2.setTower("12");
        member2.setApart("133222");
        member2.setCoefficient(1.23);
        member2.setCanVote(true);
        member2.setCodeBar("133222");

        Members member3 = new Members();
        member3.setId(3);
        member3.setName("c1@gmail.com");
        member3.setNameSecond("133222");
        member3.setTower("12");
        member3.setApart("133222");
        member3.setCoefficient(1.23);
        member3.setCanVote(true);
        member3.setCodeBar("133222");

        StepVerifier.create(membersRepository.insert(member1).log())
                .expectSubscription()
                .expectNextCount(0)
                .verifyComplete();

        StepVerifier.create(membersRepository.save(member2).log())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();

        StepVerifier.create(membersRepository.save(member3).log())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @Order(1)
    public void testListarContactos(){
        StepVerifier.create(membersRepository.findAll().log())
                .expectSubscription()
                .expectNextCount(3)
                .verifyComplete();
    }

}