package ru.hogwarts.chool.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.chool.recod.FacultyRecord;
import ru.hogwarts.chool.recod.StudentRecord;
import ru.hogwarts.chool.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentRecord createStudent(@RequestBody @Valid StudentRecord studentRecord) {
        return studentService.createStudent(studentRecord);
    }

    @GetMapping("/{studentId}")
    public StudentRecord getStudentById(@PathVariable long studentId) {
        return studentService.getStudentById(studentId);
    }


    @PutMapping("{studentId}")
    public StudentRecord updateStudent(@PathVariable long studentId, @RequestBody @Valid StudentRecord studentRecord) {
        return studentService.updateStudent(studentId, studentRecord);
    }

    @DeleteMapping("/{studentId}")
    public StudentRecord deleteStudent(@PathVariable long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping(params = "aga")
    public Collection<StudentRecord> findByAge (@RequestParam int age) {
        return studentService.findByAge(age);
    }

    @GetMapping(params = "minAge, maxAge")
    public Collection<StudentRecord> finByAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/{studentId}/faculty")
    public FacultyRecord getFacultyByStudent(@PathVariable long studentId) {
        return studentService.getFacultyByStudent(studentId);
    }

    @PatchMapping("/{studentId}/avatar")
    public StudentRecord patchStudentAvatar(@PathVariable long studentId, @RequestParam("avatarId")long avatarId) {
        return studentService.patchStudentAvatar(studentId, avatarId);
    }


}
