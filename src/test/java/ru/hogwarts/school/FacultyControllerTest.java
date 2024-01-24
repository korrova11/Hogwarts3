package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;
    Faculty faculty = new Faculty("Gvn", "5", 0l);

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFacultyId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/1",
                        String.class))
                .contains("1");
    }

    @Test
    public void testGetStudentsByFacultyId() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/1/students",
                        Collection.class)).isNotNull();
      Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/9/students",
                        String.class)).contains("500");
    }
    @Test
    public void testGetFaculty() throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty",
                        Collection.class)).isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?color=1",
                        String.class)).isNotNull();
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?name=1",
                        String.class)).isNotNull();
    }
    @Test
    public void testPostFaculty(){

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty",
                        faculty, String.class))
                .contains("Gvn");

    }
    @Test
    public void testDeletedFacultyById() throws Exception {
        //Faculty faculty = new Faculty("111",12,0l);
        Long id = this.restTemplate.postForObject("http://localhost:" + port + "/faculty",
                faculty, Faculty.class).getId();
        restTemplate.delete("http://localhost:" + port
                + "/faculty/"+id);
        Assertions
                .assertThat((this.restTemplate.getForObject("http://localhost:" + port +
                                "/faculty/"+id,
                        String.class)))
                .toString().contains("500");
    }
    @Test
    public void testPutFaculty(){
       // Faculty faculty = new Faculty("111",12,0L);
        Long id = this.restTemplate.postForObject("http://localhost:" + port + "/faculty",
                faculty, Faculty.class).getId();
        Faculty faculty1 = new Faculty("Rst","51",id);

        restTemplate.put( "http://localhost:" + port + "/faculty",faculty1);
        Assertions
                .assertThat((this.restTemplate.getForObject("http://localhost:" + port +
                                "/student/"+id,
                        String.class))).contains(""+id);

    }
}
