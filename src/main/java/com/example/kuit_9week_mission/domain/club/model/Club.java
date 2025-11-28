package com.example.kuit_9week_mission.domain.club.model;

public record Club(
        Long clubId,
        String name,
        String description,
        ClubStatus status
) {
    @Override
    public Long clubId() {
        return clubId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public ClubStatus status() {
        return status;
    }
}
