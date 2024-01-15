package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceIntTest {
    @Mock
    StudentRepository repository;
    @InjectMocks
    StudentService out;

    Student student1 = new Student("Студент1", 12, 1l);


    Student student2 = new Student("Студент2", 12, 2l);
    Student student3 = new Student("Студент3", 11, 3l);
    List<Student> studentList = new ArrayList<>(List.of(student1, student2, student3));
    Faculty faculty = new Faculty("dance", "green", 1l);

    @Test
    public void addTest() {
        when(repository.save(student2)).thenReturn(student2);
        assertEquals(out.add(student2), student2);
        verify(repository, times(1)).save(student2);
    }

    @Test
    public void findFacultyByStudentTest() {
        when(repository.getReferenceById(any())).thenReturn(student1);
        student1.faculty = faculty;
        assertEquals(out.findFacultyByStudent(1l), faculty);
    }
}