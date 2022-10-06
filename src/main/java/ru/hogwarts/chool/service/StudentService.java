package ru.hogwarts.chool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.chool.model.Student;

import java.util.*;

@Service
public class StudentService {

    Map<Long, Student> students = new HashMap<>();

    Long generatedStudentId = 0L;

    public Student creteStudent(Student student) {
        student.setId(generatedStudentId++);
        if(!students.values().equals(student)){
        students.put(generatedStudentId, student);}
        return student;
    }


    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    public Student updateStudent( Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
            return students.remove(studentId);
    }

    public Collection<Student> getAllStudent() {
        return Collections.unmodifiableCollection(students.values());
    }

    public Collection <Student> serchStudentyByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}