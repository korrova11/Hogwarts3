package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long StudentId){
        Student student = studentService.find(StudentId);
        if (student==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

        }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student createdStudent = studentService.add(student);
        return ResponseEntity.ok(createdStudent);
    }
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        Student updateStudent= studentService.change(student.getId(),student);
        if(updateStudent==null){
            return ResponseEntity.notFound().build();
                    }
        return ResponseEntity.ok(updateStudent);
    }

}
