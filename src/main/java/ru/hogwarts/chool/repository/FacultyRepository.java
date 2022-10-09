package ru.hogwarts.chool.repository;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.chool.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Id> {
    Faculty getById(Long studentId);
    Faculty deleteById(Long facultyId);
}
