package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {


    private List<Student> students = new ArrayList<>();

    @PostConstruct
    void loadData() {

        students.add(new Student("Hung", "Nguyen"));
        students.add(new Student("John", "Doe"));
    }

    @GetMapping("/students")


    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{id}")

    public Student getStudent(@PathVariable int id) {
        if (id >= students.size() || id < 0) {
            throw new StudentNotFoundException("Student id not found - " + id);
        }
        return students.get(id);
    }


}
