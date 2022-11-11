package ru.hogwarts.chool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.chool.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findAllByAgeBetween(int minAge, int maxAge);
}

