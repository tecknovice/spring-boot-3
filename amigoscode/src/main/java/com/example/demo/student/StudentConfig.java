package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student hung = new Student(1L, "Hung Nguyen", "hung@email.com", LocalDate.of(1989, 10, 22));
            Student kevin = new Student(2L, "Kevin Nguyen", "kevin@email.com", LocalDate.of(1989, 10, 22));
            studentRepository.saveAll(List.of(hung,kevin));
        };
    }
}
