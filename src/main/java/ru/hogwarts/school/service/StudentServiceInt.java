package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentServiceInt {
    Student add(Student student);
    void remove(Long id);
    Student find(Long id);
    Collection<Student> find(int age);
    Student change(Long id, Student student);
    Collection<Student> findAll();
    Collection<Student> findAgeBetween(int min, int max);
    Faculty findFacultyByStudent(long id);
}
