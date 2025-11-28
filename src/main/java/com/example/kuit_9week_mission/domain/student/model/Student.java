package com.example.kuit_9week_mission.domain.student.model;

public record Student(
        Long studentId,
        Integer studentNumber,
        String name
) {
    @Override
    public Long studentId() {
        return studentId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Integer studentNumber() {
        return studentNumber;
    }
}
