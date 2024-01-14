package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);

    }

    public void remove(Long id) {
        facultyRepository.deleteById(id);

    }

    public Faculty find(Long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty change(Long id, Faculty faculty) {

        return facultyRepository.save(faculty);

    }

    public Faculty findFacultyByColor(String color) {
        return facultyRepository.findFacultyByColorIgnoreCase(color);


    }

    public Faculty findFacultyByName(String name) {
        return facultyRepository.findFacultyByNameIgnoreCase(name);


    }

    public Collection<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public Collection<Student> findStudentByFaculty(long id) {
        return facultyRepository.getReferenceById(id).getStudents();
    }

}


