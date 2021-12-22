package com.example.student.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("Email taken!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student does not exist!");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student does not exist!"));

        if (name!= null
                && name != ""
                && name.length() > 0
                && Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        System.out.println("email: "+  email);

        if (email != null && email != ""
                && email.length() > 0
                && Objects.equals(student.getEmail(), email)) {
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
            if (optionalStudent.isPresent()) {
                throw new IllegalStateException("Email taken!");
            }
            student.setEmail(email);
        }
    }
}
