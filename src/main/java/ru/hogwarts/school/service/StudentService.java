package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;
import ru.hogwarts.school.repositiries.StudentRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student add(Student student) {
        return studentRepository.save(student);

    }

    public void remove(Long id) {
        studentRepository.deleteById(id);

    }

    public Student find(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> find(int age) {

        final List<Student> ageStudent =
                studentRepository.findAll().stream()
                        .filter(e -> e.getAge() == age)
                        .collect(Collectors.toList());

        return ageStudent;
    }


    public Student change(Long id, Student student) {

        return studentRepository.save(student);

    }
}
