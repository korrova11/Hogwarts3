package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RequestMapping("/student")
@RestController


public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.find(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

    }
    @GetMapping("/age/{studentAge}")
    public ResponseEntity<List<Student>> getStudent(@PathVariable int studentAge) {
       // List<Student> students = (java.util.List<Student>) studentService.find(studentAge);
        if (studentService.find(studentAge).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((java.util.List<Student>) studentService.find(studentAge));

    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.add(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.change(student.getId(), student);
        if (updateStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        Student removeStudent = studentService.remove(studentId);
        if (removeStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(removeStudent);


    }

}
