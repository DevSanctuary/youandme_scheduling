package com.project.youandme_schedule.dto;

import java.time.LocalDateTime;

public class ScheduleDto {
    private Long id;
    private LocalDateTime dateTime;
    private Long userId;
    private Long trainerId;

    public Long getUserId() {
        return userId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
