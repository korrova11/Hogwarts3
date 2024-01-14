package ru.hogwarts.school.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Faculty findFacultyByColorIgnoreCase(String color);
    Faculty findFacultyByNameIgnoreCase(String color);
}
