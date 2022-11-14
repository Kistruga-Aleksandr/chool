package ru.hogwarts.chool.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.chool.component.RecordMapper;
import ru.hogwarts.chool.exception.AvatarNotFoundException;
import ru.hogwarts.chool.exception.StudentNotFoundException;
import ru.hogwarts.chool.model.Avatar;
import ru.hogwarts.chool.model.Faculty;
import ru.hogwarts.chool.model.Student;
import ru.hogwarts.chool.recod.FacultyRecord;
import ru.hogwarts.chool.recod.StudentRecord;
import ru.hogwarts.chool.repository.AvatarRepository;
import ru.hogwarts.chool.repository.FacultyRepository;
import ru.hogwarts.chool.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final RecordMapper recordMapper;
    private final FacultyRepository facultyRepository;
    private final AvatarRepository avatarRepository;


    public StudentService(StudentRepository studentRepository, RecordMapper recordMapper,
                          FacultyRepository facultyRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.recordMapper = recordMapper;
        this.facultyRepository = facultyRepository;
        this.avatarRepository = avatarRepository;
    }

    public StudentRecord createStudent(StudentRecord studentRecord) {
        Student student = recordMapper.toEntity(studentRecord);
        student.setFaculty(Optional.ofNullable(student.getFaculty()).map(Faculty::getId).
                flatMap(facultyRepository::findById).orElse(null));
        return recordMapper.toRecord(studentRepository.save(student));
    }

    public StudentRecord getStudentById(long studentId) {
        return recordMapper.toRecord(studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException(studentId)));
    }

    public StudentRecord updateStudent(long studentId, StudentRecord studentRecord) {
        Student oldStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException(studentId));
        oldStudent.setName(studentRecord.getName());
        oldStudent.setAge(studentRecord.getAge());
        oldStudent.setFaculty(Optional.ofNullable(studentRecord.getFaculty()).map(FacultyRecord::getId).
                flatMap(facultyRepository::findById).orElse(null));
        return recordMapper.toRecord(studentRepository.save(oldStudent));
    }

    public StudentRecord deleteStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new StudentNotFoundException(studentId));
        studentRepository.delete(student);
        return recordMapper.toRecord(student);
    }

    public Collection<StudentRecord> findByAge(int age) {
        return studentRepository.findAllByAge(age).stream().map(recordMapper::toRecord).
                collect(Collectors.toList());
    }

    public Collection<StudentRecord> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findAllByAgeBetween(minAge, maxAge).stream().
                map(recordMapper::toRecord).collect(Collectors.toList());
    }

    public FacultyRecord getFacultyByStudent(long studentId) {
        return getStudentById(studentId).getFaculty();
    }

    public StudentRecord patchStudentAvatar(long studentId, long avatarId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Avatar> optionalAvatar = avatarRepository.findById(avatarId);

        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException(studentId);
        }

        if (optionalAvatar.isEmpty()) {
          throw new AvatarNotFoundException(avatarId);
        }
        Student student = optionalStudent.get();
        student.setAvatar(optionalAvatar.get());
        return recordMapper.toRecord(studentRepository.save(studentRepository.save(student)));
    }

}