package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;
import ru.hogwarts.school.repositiries.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student add(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);

    }

    public void remove(Long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);

    }

    public Student find(Long id) {
        return studentRepository.findById(id).get();
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for findByAge student");
        return studentRepository.findStudentByAge(age);

    }


    public Student change(Long id, Student student) {
        logger.info("Was invoked method for put student");
        return studentRepository.save(student);

    }

    public Collection<Student> findAll() {
        logger.info("Was invoked method for findAll student");
        return studentRepository.findAll();
    }

    public Collection<Student> findAgeBetween(int min, int max) {
        logger.info("Was invoked method for findByAgeBetWeen student");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudent(long id) {
        logger.info("Was invoked method for findFacultyByStudent student");
        return studentRepository.getReferenceById(id).getFaculty();

    }

    public Integer countStudents() {
        logger.info("Was invoked method for countStudents student");
        return studentRepository.countStudents();
    }

    public Integer middleAgeByStudents() {
        logger.info("Was invoked method for middleAgeStudents student");
        return studentRepository.middleAgeByStudents();
    }

   public Double middleAgeByStudentsByStream() {
        return studentRepository.findAll().stream()
               .collect((Collectors.averagingInt(Student::getAge)));
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for getLast5Students student");
        return studentRepository.getLastFiveStudentsById();
    }

    public List<Student> getStudentsBeginWithA() {
        return studentRepository.findAll().stream()
                .parallel()
                .filter(student -> student.getName().startsWith("A"))
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());


    }
}
