package com.library.service.impl;

import com.library.domain.UserRole;
import com.library.modal.User;
import com.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        initializeAdminUser();
    }

    private void initializeAdminUser(){
        String adminEmail = "turhanskiy@ukr.net";
        String adminPassword = "qwer1234";

        if (userRepository.findByEmail(adminEmail) == null){
            User user = User.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .fullName("Oleksandr Turchanskiy")
                    .role(UserRole.ADMIN)
                    .build();

            User admin = userRepository.save(user);

        }
    }

}
