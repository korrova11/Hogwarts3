package ru.hogwarts.school.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositiries.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService implements FacultyServiseInt {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        logger.info("Was invoked method for createFaculty");
        return facultyRepository.save(faculty);

    }

    public void remove(Long id) {
        logger.info("Was invoked method for deleteFaculty");
        facultyRepository.deleteById(id);

    }

    public Faculty find(Long id) {
        logger.info("Was invoked method for findFaculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty change(Long id, Faculty faculty) {
        logger.info("Was invoked method for putFaculty");
        return facultyRepository.save(faculty);

    }

    public Faculty findFacultyByColor(String color) {
        logger.info("Was invoked method for findFacultyByColor");
        return facultyRepository.findFacultyByColorIgnoreCase(color);


    }

    public Faculty findFacultyByName(String name) {
        logger.info("Was invoked method for findFacultyByName");
        return facultyRepository.findFacultyByNameIgnoreCase(name);


    }

    public Collection<Faculty> findAll() {
        logger.info("Was invoked method for findAllFaculties");
        return facultyRepository.findAll();
    }

    public Collection<Student> findStudentByFaculty(long id) {
        logger.info("Was invoked method for findStudentByFaculty");
        return facultyRepository.getReferenceById(id).getStudents();
    }

}


