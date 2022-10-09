package ru.hogwarts.chool.repository;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.chool.model.Student;

public interface StudentRepository extends JpaRepository<Student, Id> {
    Student getById(Long studentId);

    Student deleteById(Long studentId);
}

