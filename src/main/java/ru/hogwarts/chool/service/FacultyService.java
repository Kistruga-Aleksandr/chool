package ru.hogwarts.chool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.chool.model.Faculty;

import java.util.*;

@Service
public class FacultyService {

    Map<Long, Faculty> faculties = new HashMap<>();
    Long generatedFacultyId = 0L;

    public Faculty creteFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId++);
        faculties.put(generatedFacultyId,faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        return faculties.get(facultyId);
    }

    public Faculty updateFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facultyId) {
       return faculties.remove(facultyId);
    }

    public Collection<Faculty> getAllFaculty() {
        return Collections.unmodifiableCollection(faculties.values());
    }

    public Collection<Faculty> searchFacultyByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                result.add(faculty);
            }
        }
        return result;
    }
}
