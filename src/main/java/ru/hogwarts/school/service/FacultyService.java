package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();

    private static Long countf = 0L;

    public FacultyService() {
    }

    public Faculty add(Faculty faculty) {
       faculty.setId(countf++);
        facultyMap.put(faculty.getId(),faculty);
        return faculty;

    }

    public Faculty remove(Faculty faculty) {
        facultyMap.remove(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty find(Faculty faculty) throws ClassNotFoundException {

        return facultyMap.values().stream()
                .filter(e -> e.equals(faculty))
                .findFirst()
                .orElseThrow(ClassNotFoundException::new);
    }

    public Faculty change(Faculty faculty1, Faculty faculty2) {
        facultyMap.put(faculty1.getId(), faculty2);
        faculty1.setId(faculty1.getId());
        return faculty2;

    }
}


