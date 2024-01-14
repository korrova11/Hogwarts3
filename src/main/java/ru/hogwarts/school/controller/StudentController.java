package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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

    @GetMapping("/{studentId}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudent(@PathVariable Long studentId) {
        Student student = studentService.find(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        Faculty faculty = studentService.findFacultyByStudent(studentId);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/age/{studentAge}")
    public ResponseEntity<List<Student>> getStudent(@PathVariable int studentAge) {
        if (studentService.find(studentAge).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((java.util.List<Student>) studentService.find(studentAge));

    }

    @GetMapping()
    public ResponseEntity getStudent(@RequestParam(required = false) Integer min,
                                     @RequestParam(required = false) Integer max) {
        if (min > max) {
            return ResponseEntity.badRequest().build();
        }

        if (min != null && max != null) {
            return ResponseEntity.ok(studentService.findAgeBetween(min, max));
        }

        return ResponseEntity.ok(studentService.findAll());
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
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        studentService.remove(studentId);
        return ResponseEntity.ok().build();


    }

}
