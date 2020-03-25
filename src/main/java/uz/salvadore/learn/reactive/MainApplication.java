package uz.salvadore.learn.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.salvadore.learn.reactive.entity.User;
import uz.salvadore.learn.reactive.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository repository) {
        log.info("Begin loading data...");
        return args -> {
            List<User> userList = new ArrayList<>();
            userList.add(new User(0, "Eldar", "Sagitov", 32));
            userList.add(new User(0, "Daniya", "Sagitova", 30));
            userList.add(new User(0, "Sevilya", "Sagitova", 6));
            userList.add(new User(0, "Basir", "Sagitov", 2));
            userList.add(new User(0, "Elzhara", "Sagitova", 1));
            userList.add(new User(0, "Emin", "Masumov", 34));
            userList.add(new User(0, "Igor", "Denisov", 30));
            userList.add(new User(0, "Sergey", "Sokolov", 32));
            userList.add(new User(0, "Jonibek", "Abdurakhmanov", 30));
            userList.add(new User(0, "Eugene", "Ulyanov", 32));
            for (User u : userList) {
                log.info("Saved user: {}", repository.save(u));
            }
        };
    }

}
