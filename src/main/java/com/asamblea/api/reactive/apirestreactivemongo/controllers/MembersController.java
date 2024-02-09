package com.asamblea.api.reactive.apirestreactivemongo.controllers;

import com.asamblea.api.reactive.apirestreactivemongo.documents.Members;
import com.asamblea.api.reactive.apirestreactivemongo.repository.MembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class MembersController {

    @Autowired
    private MembersRepository membersRepository;

    @GetMapping("/Members")
    public Flux<Members> listMembers(){
        return membersRepository.findAll();
    }

    @GetMapping("/Members/{id}")
    public Mono<ResponseEntity<Members>> getMembers(@PathVariable String id){
        return membersRepository.findById(id)
                .map(members -> new ResponseEntity<>(members, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/Members/byCodeBar/{CodeBar}")
    public Mono<ResponseEntity<Members>> getMembersForCodeBar( @PathVariable String CodeBar){
        return membersRepository.findFirstByCodeBar(CodeBar)
                .map(members -> new ResponseEntity<>(members, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/Members")
    public Mono<ResponseEntity<Members>> saveMember(@RequestBody Members members){
        return membersRepository.insert(members)
                .map(members1 -> new ResponseEntity<>(members1,HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(members,HttpStatus.NOT_ACCEPTABLE));
    }

    @PutMapping("/Members/{id}")
    public Mono<ResponseEntity<Members>> editMember(@RequestBody Members members,@PathVariable String id){
        return membersRepository.findById(id)
                .flatMap(membersUpdate -> {
                    members.setId(Integer.parseInt(id));
                    return membersRepository.save(members)
                            .map(members1 -> new ResponseEntity<>(members1, HttpStatus.ACCEPTED));
                })
                .defaultIfEmpty(new ResponseEntity<>(members,HttpStatus.NOT_FOUND));

    }

    @DeleteMapping(value = "/Members/{id}")
    public Mono<Void> deleteMembers( @PathVariable String id){
        return membersRepository.deleteById(id);
    }
}
