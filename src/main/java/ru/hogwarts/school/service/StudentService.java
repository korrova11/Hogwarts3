package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> studentMap = new HashMap<>();
    private static Long count = 0L;

    public StudentService(Map<Long, Student> studentMap) {
        this.studentMap = studentMap;

    }

    public StudentService() {
    }

    public Student add(Student student) {
        student.setId(++count);
        studentMap.put(student.getId(), student);
        return student;

    }

    public Student remove(Long id) {

        studentMap.remove(id);
        return studentMap.get(id);
    }

    public Student find(Long id)  {

       /* return studentMap.values().stream()
                .filter(e -> e.equals(student))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);*/
        return studentMap.get(id);
    }
    public List<Student> find(int age) {


        final List<Student> ageStudent =
                studentMap.values().stream()
                        .filter(e -> e.getAge() == age)
                        .collect(Collectors.toList());

        return ageStudent;
    }


        public Student change(Long id,Student student) {
        studentMap.put(id, student);
        student.setId(id);
        return student;

    }
}
