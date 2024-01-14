package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyServiseInt {
    Faculty add(Faculty faculty);
    void remove(Long id);
    Faculty find(Long id);
    Faculty change(Long id, Faculty faculty);
    Faculty findFacultyByColor(String color);
    Faculty findFacultyByName(String name);
    Collection<Faculty> findAll();
    Collection<Student> findStudentByFaculty(long id);
}
