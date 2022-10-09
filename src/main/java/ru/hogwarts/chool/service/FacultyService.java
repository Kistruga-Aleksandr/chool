package ru.hogwarts.chool.service;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.stereotype.Service;
import ru.hogwarts.chool.model.Faculty;
import ru.hogwarts.chool.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty creteFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long studentId) {
        return facultyRepository.getById(studentId);
    }

    public Faculty updateFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facultyId) {
       return facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> getAllFaculty() {
        return Collections.unmodifiableCollection(facultyRepository.findAll());
    }

    public Collection<Faculty> searchFacultyByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (faculty.getColor().equals(color)) {
                result.add(faculty);
            }
        }
        return result;
    }
}
