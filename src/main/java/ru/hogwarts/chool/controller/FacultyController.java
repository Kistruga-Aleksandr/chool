package ru.hogwarts.chool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.chool.model.Faculty;
import ru.hogwarts.chool.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RequestMapping("/faculty")

@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty creteFaculty(@RequestBody Faculty faculty) {
        return facultyService.creteFaculty(faculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

  @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.updateFaculty(faculty);
        if (faculty1 == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{facultyId}")
    public Faculty deleteFaculty(@PathVariable Long facultyId) {
        return facultyService.deleteFaculty(facultyId);
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> searchFacultyByColor(@RequestParam(required = false) String color) {
        if (color != null && color.isBlank()) {
            return ResponseEntity.ok(facultyService.searchFacultyByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
