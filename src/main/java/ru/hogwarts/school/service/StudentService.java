package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;
import ru.hogwarts.school.repositiries.StudentRepository;

import java.util.*;
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

    public Collection<Student> findByAge(int age) {
        return studentRepository.findStudentByAge(age);

    }


    public Student change(Long id, Student student) {
        return studentRepository.save(student);

    }

    public Collection<Student> findAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> findAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudent(long id) {
        return studentRepository.getReferenceById(id).getFaculty();

    }
    public Integer countStudents(){
        return  studentRepository.countStudents();
    }
    public Integer   middleAgeByStudents(){
        return  studentRepository.middleAgeByStudents();
    }
    public List<Student>   getLastFiveStudents(){
        return  studentRepository.getLastFiveStudentsById();
    }
}
