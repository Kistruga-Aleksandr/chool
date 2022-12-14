package ru.hogwarts.chool.component;

import org.springframework.stereotype.Component;
import ru.hogwarts.chool.model.Avatar;
import ru.hogwarts.chool.model.Faculty;
import ru.hogwarts.chool.model.Student;
import ru.hogwarts.chool.recod.AvatarRecord;
import ru.hogwarts.chool.recod.FacultyRecord;
import ru.hogwarts.chool.recod.StudentRecord;

@Component
public class RecordMapper {

    public StudentRecord toRecord(Student student) {
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setId(student.getId());
        studentRecord.setName(student.getName());
        studentRecord.setAge(student.getAge());
        if (student.getFaculty() != null) {
            studentRecord.setFaculty(toRecord(student.getFaculty()));
        }
        return studentRecord;
    }

    public FacultyRecord toRecord(Faculty faculty) {
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(faculty.getId());
        facultyRecord.setName(faculty.getName());
        facultyRecord.setColor(faculty.getColor());
        return facultyRecord;
    }

    public AvatarRecord toRecord(Avatar avatar) {
        return new AvatarRecord(avatar.getId(),avatar.getMediaType(),"http://localhost:8080/avatar"
                + avatar.getId() + "/from-db");
    }

    public Student toEntity(StudentRecord studentRecord) {
        Student student = new Student();
        student.setName(studentRecord.getName());
        student.setAge(studentRecord.getAge());
        if (studentRecord.getFaculty() != null) {
            Faculty faculty = toEntity(studentRecord.getFaculty());
            faculty.setId(studentRecord.getFaculty().getId());
            student.setFaculty(faculty);
        }
        return student;
    }

    public Faculty toEntity(FacultyRecord facultyRecord) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyRecord.getName());
        faculty.setColor(facultyRecord.getColor());
        return faculty;
    }


}
