package com.example.kuit_9week_mission.domain.club.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubMemberRepository {

    private final JdbcTemplate jdbc;

    // TODO: TODO 6을 구현하기 위해선 Club_Members 와 Clubs 테이블간의 JOIN 을 적절히 활용해야한다!
    public boolean exists(Long studentId, Long clubId) {
        String sql = "SELECT COUNT(*) FROM Club_Members WHERE student_id = ? AND club_id = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, studentId, clubId);
        return count != null && count > 0;
    }

    public void save(Long studentId, Long clubId) {
        String sql = "INSERT INTO Club_Members (student_id, club_id, join_date) VALUES (?, ?, NOW())";
        jdbc.update(sql, studentId, clubId);
    }

    public List<String> findClubNamesByStudent(Long studentId) {
        String sql =
                "SELECT c.name " +
                        "FROM Club_Members cm " +
                        "JOIN Clubs c ON cm.club_id = c.club_id " +
                        "WHERE cm.student_id = ?";
        return jdbc.query(sql, (rs, i) -> rs.getString("name"), studentId);
    }

}
