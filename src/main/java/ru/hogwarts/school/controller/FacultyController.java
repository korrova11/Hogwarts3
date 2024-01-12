package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long FacultyId) {
        Faculty faculty = facultyService.find(FacultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);

    }
    @GetMapping("/{color}")
    public ResponseEntity<List<Faculty>> getStudent(@PathVariable String color) {
        // List<Student> students = (java.util.List<Student>) studentService.find(studentAge);
        if (facultyService.find(color).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((java.util.List<Faculty>) facultyService.find(color));

    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.add(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.change(faculty.getId(), faculty);
        if (updateFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long facultyId) {
       facultyService.remove(facultyId);
               return ResponseEntity.ok().build();

    }

}
