package com.example.kuit_9week_mission.domain.student.dto.response;

import com.example.kuit_9week_mission.domain.student.model.Student;

public record StudentInfoResponse(
        Long studentId,
        Integer studentNumber,
        String name
) {
    public static StudentInfoResponse from(Student student) {
        return new StudentInfoResponse(student.studentId(), student.studentNumber(), student.name());
    }
}