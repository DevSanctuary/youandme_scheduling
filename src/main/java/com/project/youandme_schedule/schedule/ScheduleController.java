package com.project.youandme_schedule.schedule;

import com.project.youandme_schedule.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/register")
    public ResponseEntity<String> registerSchedule(@RequestBody ScheduleDto dto) {
        String message = scheduleService.registerSchedule(dto);
        return ResponseEntity.ok(message);
    }
}