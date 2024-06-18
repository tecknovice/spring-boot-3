package com.example.demo.student;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> readAll() {
        return studentRepository.findAll();

    }

    public Student create(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) throw new IllegalStateException("Email already exists");
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(Long studentId, String name, String email) throws BadRequestException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student id does not exist : " + studentId));
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) student.setName(name);
        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) throw new BadRequestException("Email already exists");
            student.setEmail(email);
        }
        return student;
    }

    public void delete(Long studentId) throws BadRequestException {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) throw new BadRequestException("Student id does not exists  - " + studentId);
        studentRepository.deleteById(studentId);
    }


}
