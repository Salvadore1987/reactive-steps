package uz.salvadore.learn.reactive.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import uz.salvadore.learn.reactive.dto.PageRequest;
import uz.salvadore.learn.reactive.entity.User;
import uz.salvadore.learn.reactive.service.ReactiveCrudService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ReactiveCrudService<User> crudService;

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> users(@RequestBody PageRequest pageRequest) {
        return crudService.findAll(org.springframework.data.domain.PageRequest.of(pageRequest.getPage(), pageRequest.getSize()));
    }

}
