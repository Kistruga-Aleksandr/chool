package ru.hogwarts.chool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.chool.model.Faculty;
import ru.hogwarts.chool.recod.FacultyRecord;
import ru.hogwarts.chool.recod.StudentRecord;
import ru.hogwarts.chool.service.FacultyService;

import javax.validation.Valid;
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
    public FacultyRecord createFaculty(@RequestBody FacultyRecord facultyRecord) {
        return facultyService.createFaculty(facultyRecord);
    }

    @GetMapping("/{facultyId}")
    public FacultyRecord getFacultyInfo(@PathVariable long facultyId) {
        return facultyService.getFacultyById(facultyId);
    }
  @PutMapping("/{facultyId}")
    public FacultyRecord updateFaculty(@PathVariable long facultyId,
                                       @RequestBody @Valid FacultyRecord facultyRecord) {
      return facultyService.updateFaculty(facultyId, facultyRecord);
    }

    @DeleteMapping("/{facultyId}")
    public FacultyRecord deleteFaculty(@PathVariable long facultyId) {
        return facultyService.deleteFaculty(facultyId);
    }

    @GetMapping("color")
    public Collection<FacultyRecord> findAllByColor (@RequestParam String color) {
        return facultyService.findAllByColor(color);
    }

    @GetMapping(params = "colorOrName")
    public Collection<FacultyRecord> findByColorOrName(@RequestParam String colorOrName) {
        return facultyService.findAllByColor(colorOrName);
    }

    @GetMapping("/{facultyId}/students")
    public Collection<StudentRecord> getStudentsByFaculty(@PathVariable long facultyId) {
        return facultyService.getStudentByFaculty(facultyId);
    }
}
