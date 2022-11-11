package ru.hogwarts.chool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.chool.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findAllByColor(String color);

    Collection<Faculty> findAllByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

}
