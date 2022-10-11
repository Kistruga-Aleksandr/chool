package ru.hogwarts.chool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.chool.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

}
