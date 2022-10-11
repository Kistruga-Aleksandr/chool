package ru.hogwarts.chool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.chool.model.Student;
import ru.hogwarts.chool.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student creteStudent(@RequestBody Student student) {
        return studentService.creteStudent(student);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PutMapping("{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student student1 = studentService.updateStudent(student);
        if (student1 == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age")
    public  ResponseEntity<Collection<Student>> serchStudentyByAge (@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.serchStudentyByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

}
