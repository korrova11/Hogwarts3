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

    public List<Student> findAll() {
        logger.info("Was invoked method for findAll student");
        return studentRepository.findAll();
    }

    public List<Student> findAgeBetween(int min, int max) {
        logger.info("Was invoked method for findByAgeBetWeen student");
        return studentRepository.findByAgeBetween(min, max).stream().toList();
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
    public Double middleAgeByStudentsByStream1() {
        return studentRepository.findAll().stream()
               .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
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
    public void printName(int i){
        System.out.println(findAll().get(i).getName());
    }
    public void printNameParallel(){
        printName(0);
        new Thread(()->{
            printName(2);
            printName(3);
        }).start();new Thread(()->{
            printName(4);
            printName(5);
        }).start();

        printName(1);

    }
    public synchronized void printNameSynh(int i){
        System.out.println(findAll().get(i).getName());
    }
    public void printNameParallelSynh(){
        printNameSynh(0);
        new Thread(()->{
            printNameSynh(2);
            printNameSynh(3);
        }).start();new Thread(()->{
            printNameSynh(4);
            printNameSynh(5);
        }).start();

        printNameSynh(1);

    }

}
