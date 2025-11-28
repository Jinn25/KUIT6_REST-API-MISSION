package com.example.kuit_9week_mission.domain.club.service;

import com.example.kuit_9week_mission.domain.club.model.Club;
import com.example.kuit_9week_mission.domain.club.repository.ClubRepository;
import com.example.kuit_9week_mission.global.common.exception.CustomException;
import com.example.kuit_9week_mission.global.common.response.ApiResponse;
import com.example.kuit_9week_mission.global.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    // TODO 1: 동아리 목록 조회 기능 구현(토큰 불필요) - GET (무한 스크롤 - 각 페이지당 5개의 데이터를 보여줄 것 & status 기반 필터링)
    /**
     * 응답 DTO 구조는 아래와 같은 형태를 따를 것
     * {
     *   "isSuccess": true,
     *   "statusCode": 20000,
     *   "message": "요청에 성공했습니다",
     *   "data": {
     *       "data": [
     *           {
     *               "clubId": 4,
     *               "name": "동아리 4",
     *               "description": "동아리 4 설명"
     *           }
     *       ],
     *       "lastId": 100,
     *       "hasNext": true
     *   },
     *   "timestamp": "2025-10-24T00:37:07.469931"
     * }
     */
    public ApiResponse<?> getClubs(Long lastId, String status) {
        long cursor = (lastId == null) ? Long.MAX_VALUE : lastId;

        List<Club> clubs = clubRepository.findClubs(cursor, status);

        boolean hasNext = clubs.size() == 5;
        Long nextLastId = clubs.isEmpty() ? null : clubs.get(clubs.size() - 1).clubId();

        Map<String, Object> responseData = Map.of(
                "data", clubs,
                "lastId", nextLastId,
                "hasNext", hasNext
        );

        return ApiResponse.ok(responseData);
    }


    // TODO 2: 동아리 정보 수정 기능 구현(토큰 불필요) - PUT
    public ApiResponse<?> updateClub(Club club) {

        Club existing = clubRepository.findById(club.clubId());
        if (existing == null) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        Club updated = new Club(
                existing.clubId(),
                club.name(),
                club.description(),
                existing.status()
        );

        clubRepository.updateClub(updated);

        return ApiResponse.ok(updated);
    }


    // TODO 3: 동아리 삭제 기능 구현(토큰 불필요) - DELETE
    public ApiResponse<?> deleteClub(Long clubId) {
        Club existing = clubRepository.findById(clubId);
        if (existing == null) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        clubRepository.deleteClub(clubId);
        return ApiResponse.ok(existing);
    }
}
