package com.example.kuit_9week_mission.domain.student.controller;

import com.example.kuit_9week_mission.domain.club.service.ClubMemberService;
import com.example.kuit_9week_mission.domain.student.dto.request.UpdateStudentNameRequest;
import com.example.kuit_9week_mission.domain.student.dto.response.StudentInfoResponse;
import com.example.kuit_9week_mission.domain.student.service.StudentService;
import com.example.kuit_9week_mission.global.common.auth.StudentId;
import com.example.kuit_9week_mission.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ClubMemberService clubMemberService;

    // 학생 본인 정보 조회
    // [ @StudentId Long 변수명 ] <- 이 형태로 코드를 작성해두기만 하면, 자동으로 토큰으로부터 studentId 를 추출하여 변수에 값을 주입해준다.
    @GetMapping("/me")
    public ApiResponse<StudentInfoResponse> me(@StudentId Long studentId) {
        return ApiResponse.ok(studentService.getStudentInfo(studentId));
    }

    @PatchMapping("/me")
    public ApiResponse<?> updateName(
            @StudentId Long studentId,
            @RequestBody UpdateStudentNameRequest request
    ) {
        studentService.updateStudentName(studentId, request.name());
        return ApiResponse.ok(null);
    }
}
