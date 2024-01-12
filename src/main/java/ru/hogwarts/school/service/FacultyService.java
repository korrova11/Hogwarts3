package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositiries.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
   private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
       return facultyRepository.save(faculty);

    }

    public void remove(Long id) {
        facultyRepository.deleteById(id);

    }

    public Faculty find(Long id)  {
        return facultyRepository.findById(id).get();
    }

    public Faculty change(Long id,Faculty faculty) {

        return facultyRepository.save(faculty);

    }
    public List<Faculty> find(String color) {


        final List<Faculty> colorFaculty =
                facultyRepository.findAll().stream()
                        .filter(e -> e.getColor().equals(color))
                        .collect(Collectors.toList());

        return colorFaculty;
    }
}


