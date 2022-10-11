package ru.hogwarts.chool.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.chool.model.Student;
import ru.hogwarts.chool.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student creteStudent(Student student) {
       return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {

        return studentRepository.findById(studentId).get();
    }

    public Student updateStudent( Student student) {
       return studentRepository.save(student);
    }

    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Collection<Student> getAllStudent() {
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    public Collection <Student> serchStudentyByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}