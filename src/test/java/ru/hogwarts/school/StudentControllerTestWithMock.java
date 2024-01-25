package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTestWithMock {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;
    Long id = 1L;
    String name = "Smile";
    int age = 14;
    Student student = new Student(name, age, id);

    @Test
    public void saveStudentTest() throws Exception {

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);
        studentObject.put("id", 1L);

        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    public void getStudentByIdTest() throws Exception {

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    public void getFacultyByStudentTest() throws Exception {
        Long idFaculty = 1L;
        String nameFaculty = "Culture";
        String color = "Green";

        Faculty faculty = new Faculty(nameFaculty, color, idFaculty);

        student.setFaculty(faculty);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        when(studentRepository.getReferenceById(any(Long.class))).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/1/faculty") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(idFaculty))
                .andExpect(jsonPath("$.name").value(nameFaculty))
                .andExpect(jsonPath("$.color").value(color));

    }

    @Test
    public void getStudentsByAgeTest() throws Exception {
        List<Student> studentsTest = new ArrayList<>
                (List.of(new Student("111", 12, 1L), new Student("222", 12, 2L)));
        when(studentRepository.findStudentByAge(any(Integer.class))).thenReturn(studentsTest);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age/12") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn(); //receive
        Assertions.assertTrue(result.getResponse().getContentAsString().toString().contains("111"));
        Assertions.assertTrue(result.getResponse().getContentAsString().toString().contains("222"));


    }

    @Test
    public void getStudentsByAgeBetweenOrAll() throws Exception {
        List<Student> studentsTest1 = new ArrayList<>
                (List.of(new Student("111", 12, 1L), new Student("222", 12, 2L)));
        when(studentRepository.findByAgeBetween(any(Integer.class), any(Integer.class)))
                .thenReturn(studentsTest1);
        when(studentRepository.findAll())
                .thenReturn(studentsTest1);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?min=1&max=13") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn(); //receive
        Assertions.assertTrue(result.getResponse().getContentAsString().toString().contains("111"));
        Assertions.assertTrue(result.getResponse().getContentAsString().toString().contains("222"));

        MvcResult result1 = mockMvc.perform(MockMvcRequestBuilders
                        .get("/student") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn(); //receive
        Assertions.assertTrue(result1.getResponse().getContentAsString().toString().contains("111"));
        Assertions.assertTrue(result1.getResponse().getContentAsString().toString().contains("222"));
    }

    @Test
    public void updateStudentTest() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);
        studentObject.put("id", 1L);


        when(studentRepository.save(any())).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

}
