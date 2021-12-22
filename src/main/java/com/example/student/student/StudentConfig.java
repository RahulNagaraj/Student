package com.example.student.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student rahul = new Student(
                    "Rahul",
                    LocalDate.of(1994, Month.JUNE, 07),
                    "ra@g.com"
            );

            Student vidhi = new Student(
                    "Vidhi",
                    LocalDate.of(1997, Month.OCTOBER, 11),
                    "vi@g.com"
            );

            repository.saveAll(
                    List.of(rahul, vidhi)
            );
        };
    }
}
