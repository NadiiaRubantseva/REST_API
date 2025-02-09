package ua.edu.ztu.nadiiarubantseva.restapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.edu.ztu.nadiiarubantseva.restapi.user.model.Role;
import ua.edu.ztu.nadiiarubantseva.restapi.user.model.User;
import ua.edu.ztu.nadiiarubantseva.restapi.user.repository.UserRepository;

@Configuration
public class DataInitializer {

    // for test purposes
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                userRepository.save(user);
            }
        };
    }
}
