package com.dave.konvergenz;

import com.dave.konvergenz.backend.authentication.entity.Role;
import com.dave.konvergenz.backend.authentication.service_class.impl.StaffDetailsServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KonvergenzApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KonvergenzApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(StaffDetailsServiceImpl staffDetailsService){
        return args -> {
            staffDetailsService.saveRoles(new Role( "ROLE_ADMIN"));
            staffDetailsService.saveRoles(new Role( "ROLE_USER"));
            staffDetailsService.saveRoles(new Role( "ROLE_HR"));
        };
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
