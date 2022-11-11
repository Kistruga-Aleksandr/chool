package ru.hogwarts.chool.exception;

public class FacultyNotFoundException extends RuntimeException {

    private final long facultyId;

    public FacultyNotFoundException(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getId() {
        return facultyId;
    }
}
