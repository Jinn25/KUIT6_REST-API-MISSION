package com.example.kuit_9week_mission.domain.club.controller;

import com.example.kuit_9week_mission.domain.club.model.Club;
import com.example.kuit_9week_mission.domain.club.service.ClubMemberService;
import com.example.kuit_9week_mission.domain.club.service.ClubService;
import com.example.kuit_9week_mission.global.common.auth.StudentId;
import com.example.kuit_9week_mission.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final ClubMemberService clubMemberService;

    @GetMapping
    public ApiResponse<?> getClubs(
            @RequestParam(required = false) Long lastId,
            @RequestParam String status
    )
    {
        return clubService.getClubs(lastId, status);
    }

    @PutMapping
    public ApiResponse<?> updateClub(@RequestBody Club club) {
        return clubService.updateClub(club);
    }

    @DeleteMapping("/{clubId}")
    public ApiResponse<?> deleteClub(@PathVariable Long clubId) {
        return clubService.deleteClub(clubId);
    }

    @PostMapping("/{clubId}/join")
    public ApiResponse<?> joinClub(
            @StudentId Long studentId,
            @PathVariable Long clubId
    ) {
        return clubMemberService.joinClub(studentId, clubId);
    }

    @GetMapping("/me")
    public ApiResponse<?> myClubs(@StudentId Long studentId) {
        return clubMemberService.getMyClubs(studentId);
    }
}
