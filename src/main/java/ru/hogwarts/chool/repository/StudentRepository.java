package ru.hogwarts.chool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.chool.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

