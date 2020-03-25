package uz.salvadore.learn.reactive.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveCrudService<T> {

    Mono<T> create(T entity);
    Mono<T> update(T entity);
    Mono<T> find(T entity);
    Flux<T> findAll(Pageable pageable);
    Mono<Void> delete(T entity);

}
