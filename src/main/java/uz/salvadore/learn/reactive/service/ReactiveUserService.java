package uz.salvadore.learn.reactive.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import uz.salvadore.learn.reactive.entity.User;
import uz.salvadore.learn.reactive.repository.UserRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReactiveUserService implements ReactiveCrudService<User> {

    @Autowired
    private UserRepository repository;

    // TODO Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed,
    //  and run it with an elastic scheduler
    /*Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll())).subscribeOn(Schedulers.elastic());
    }*/

    // TODO Insert users contained in the Flux parameter in the blocking repository using an elastic scheduler
    //  and return a Mono<Void> that signal the end of the operation
    /*Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
        return flux
                .publishOn(Schedulers.elastic())
                .doOnNext(u -> repository.save(u))
                .then();
    }*/

    @Override
    public Mono<User> create(User entity) {
        return Mono.defer(() -> Mono.just(repository.save(entity)).subscribeOn(Schedulers.elastic()));
    }

    @Override
    public Mono<User> update(User entity) {
        return Mono.defer(() -> Mono.just(repository.save(entity)).subscribeOn(Schedulers.elastic()));
    }

    @Override
    public Mono<User> find(User entity) {
        return Mono.defer(() -> Mono.just(Objects.requireNonNull(repository.findById(entity.getId()).orElse(null))).subscribeOn(Schedulers.elastic()));
    }

    @Override
    public Flux<User> findAll(Pageable pageable) {
        //return Flux.fromIterable(repository.findAll(pageable)).subscribeOn(Schedulers.elastic());
        return Flux.defer(() -> Flux.fromIterable(repository.findAll(pageable)).subscribeOn(Schedulers.elastic()));
    }

    @Override
    public Mono<Void> delete(User entity) {
        return Mono.fromRunnable(() -> repository.delete(entity));
    }
}
