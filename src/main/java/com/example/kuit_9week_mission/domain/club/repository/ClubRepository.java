package com.example.kuit_9week_mission.domain.club.repository;

import com.example.kuit_9week_mission.domain.club.model.Club;
import com.example.kuit_9week_mission.domain.club.model.ClubStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClubRepository {

    private final JdbcTemplate jdbc;

    private static final RowMapper<Club> MAPPER = (ResultSet rs, int rowNum) -> new Club(
            rs.getLong("club_id"),
            rs.getString("name"),
            rs.getString("description"),
            ClubStatus.valueOf(rs.getString("status"))
    );

    public List<Club> findClubs(Long lastId, String status) {

        String sql =
                "SELECT club_id, name, description, status " +
                        "FROM Clubs " +
                        "WHERE status = ? AND club_id < ? " +
                        "ORDER BY club_id DESC " +
                        "LIMIT 5";

        return jdbc.query(sql, MAPPER, status, lastId);
    }

    public void updateClub(Club club) {

        String sql =
                "UPDATE Clubs " +
                        "SET name = ?, description = ? " +
                        "WHERE club_id = ?";

        jdbc.update(sql, club.name(), club.description(), club.clubId());
    }

    public void deleteClub(Long clubId) {

        String sql = "DELETE FROM Clubs WHERE club_id = ?";

        jdbc.update(sql, clubId);
    }

    public Club findById(Long clubId) {
        String sql = "SELECT club_id, name, description, status FROM Clubs WHERE club_id = ?";

        List<Club> result = jdbc.query(sql, MAPPER, clubId);
        return result.isEmpty() ? null : result.get(0);
    }
}
