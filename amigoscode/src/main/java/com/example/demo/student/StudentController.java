package com.example.demo.student;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> readAll() {
        return studentService.readAll();
    }

    @PostMapping
    public Student create(@RequestBody  Student student){
        return studentService.create(student);
    }

    @PutMapping(path="{studentId}")
    public Student update(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) throws BadRequestException {
        return studentService.update(studentId,name,email);
    }

    @DeleteMapping("{studentId}")
    public void delete(@PathVariable("studentId") Long id) throws BadRequestException {
         studentService.delete(id);
    }
}
