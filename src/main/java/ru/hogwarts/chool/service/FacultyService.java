package ru.hogwarts.chool.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.chool.component.RecordMapper;
import ru.hogwarts.chool.exception.FacultyNotFoundException;
import ru.hogwarts.chool.model.Faculty;
import ru.hogwarts.chool.recod.FacultyRecord;
import ru.hogwarts.chool.recod.StudentRecord;
import ru.hogwarts.chool.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final RecordMapper recordMapper;

    public FacultyService(FacultyRepository facultyRepository,
                          RecordMapper recordMapper) {
        this.facultyRepository = facultyRepository;
        this.recordMapper = recordMapper;
    }


    public FacultyRecord createFaculty(FacultyRecord facultyRecord) {
       return recordMapper.toRecord(facultyRepository.save(recordMapper.toEntity(facultyRecord)));
    }

    public FacultyRecord getFacultyById(long facultyId) {
        return recordMapper.toRecord(facultyRepository.findById(facultyId).orElseThrow(()->
                new FacultyNotFoundException(facultyId)));
    }

    public FacultyRecord updateFaculty(long facultyId, FacultyRecord facultyRecord) {
        Faculty oldFaculty = facultyRepository.findById(facultyId).orElseThrow(() ->
                new FacultyNotFoundException(facultyId));
        oldFaculty.setName(facultyRecord.getName());
        oldFaculty.setColor(facultyRecord.getColor());
       return recordMapper.toRecord(facultyRepository.save(oldFaculty));
    }

    public FacultyRecord deleteFaculty(long facultyId) {
       Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() ->
               new FacultyNotFoundException(facultyId));
       facultyRepository.delete(faculty);
        return recordMapper.toRecord(faculty);
    }

    public Collection<FacultyRecord> findAllByColor(String color) {
        return facultyRepository.findAllByColor(color).stream().
                map(recordMapper::toRecord).collect(Collectors.toList());
    }

    public Collection<FacultyRecord> findByColorOrName(String colorOrName) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(colorOrName, colorOrName).
                stream().map(recordMapper::toRecord).collect(Collectors.toList());
    }

    public Collection<StudentRecord> getStudentByFaculty(long facultyId) {
        return facultyRepository.findById(facultyId).map(Faculty::getStudents).
                map(students -> students.stream().map(recordMapper::toRecord).collect(Collectors.toList())).
                orElseThrow(() -> new FacultyNotFoundException(facultyId));
    }
}
