package uz.salvadore.learn.reactive.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.salvadore.learn.reactive.dto.PageRequest;
import uz.salvadore.learn.reactive.entity.User;
import uz.salvadore.learn.reactive.service.ReactiveCrudService;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private ReactiveCrudService<User> crudService;

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> users(@RequestBody PageRequest pageRequest) {
        return crudService
                .findAll(org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize()));
    }

    @PostMapping(value = "/list/{age}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> usersFilter(@RequestBody PageRequest pageRequest, @PathVariable("age") Integer age) {
        return crudService
                .findAll(org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize()))
                .filter(i -> i.getAge() == age);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<User> create(@RequestBody User user) {
        return crudService.create(user);
    }

}
