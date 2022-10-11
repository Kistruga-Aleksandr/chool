package ru.hogwarts.chool.service;


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
       return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long facultyId) {
        return facultyRepository.getById(facultyId);
    }

    public Faculty updateFaculty(Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
       facultyRepository.deleteById(facultyId);
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
