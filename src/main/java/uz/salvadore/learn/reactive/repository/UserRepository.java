package uz.salvadore.learn.reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salvadore.learn.reactive.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
