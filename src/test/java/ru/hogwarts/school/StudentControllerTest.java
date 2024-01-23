package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.hibernate.annotations.NotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;
   Student student = new Student("111",12,0l);
    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    public void testGetStudentAll() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }
    @Test
    public void testGetStudentbyId() throws Exception{
               Assertions
                .assertThat((this.restTemplate.getForObject("http://localhost:" + port + "/student/1",
                        String.class)))
                       .contains("1");
    }
    @Test
    public void testGetFacultyByStudentId() throws Exception{
               Assertions
                .assertThat((this.restTemplate.getForObject("http://localhost:" + port + "/student/1/faculty", Faculty.class)))
                       .as(String.valueOf(Faculty.class));
    }
    @Test
    public void testGetListStudentsByStudentAge() throws Exception{
        Assertions
                .assertThat((this.restTemplate.getForObject("http://localhost:" + port + "/student/age/12", List.class)))
                .isNotNull();
    }
    @Test
    public void testGetListStudentsByStudentAgeWhenBadAge() throws Exception{
        Assertions
                .assertThat((this.restTemplate.getForObject("http://localhost:" + port + "/student/age/0",
                        ResponseEntity.class)))
                        .toString().contains("404");

    }
    @Test
    public void testPostStudent(){
       Student student = new Student("111",12,0l);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student",
                        student, String.class))
                                .contains("111");

    }
   @Test
    public void testDeletedStudentById() throws Exception {
       Student student = new Student("111",12,0l);
       Long id = this.restTemplate.postForObject("http://localhost:" + port + "/student",
               student, Student.class).getId();
                restTemplate.delete("http://localhost:" + port
                                + "/student/"+id);
       Assertions
               .assertThat((this.restTemplate.getForObject("http://localhost:" + port +
                               "/student/"+id,
                       String.class)))
               .toString().contains("500");
    }
    /*@Test
    public void testPutStudent(){
        Student student = new Student("111",12,283L);
        Assertions
                .assertThat(this.restTemplate.put( "http://localhost:" + port + "/student",student))





    }*/
}
