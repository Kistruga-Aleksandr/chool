package ru.hogwarts.chool.exception;

public class StudentNotFoundException extends RuntimeException {
    private final long studentId;
    public StudentNotFoundException( long studentId) {
        this.studentId = studentId;
    }

    public long getId() {
        return studentId;
    }
}
