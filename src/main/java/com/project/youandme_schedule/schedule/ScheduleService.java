package com.project.youandme_schedule.schedule;

import com.project.youandme_schedule.dto.ScheduleDto;
import com.project.youandme_schedule.trainer.Trainer;
import com.project.youandme_schedule.trainer.TrainerRepository;
import com.project.youandme_schedule.user.User;
import com.project.youandme_schedule.user.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository ScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public String registerSchedule(ScheduleDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        Optional<Trainer> trainer = trainerRepository.findById(dto.getTrainerId());

        if (!user.isPresent() || !trainer.isPresent()) {
            throw new RuntimeException("회원이나 강사를 찾을 수 없습니다.");
        }

        Schedule schedule = new Schedule();
        schedule.setDateTime(dto.getDateTime());
        schedule.setUser(user.get());
        schedule.setTrainer(trainer.get());

        ScheduleRepository.save(schedule);

        if (schedule.getDateTime() == null) {
            return "결제가 완료되었습니다. 캘린더에 예약된 날짜를 등록하고 싶으시다면 '일정등록' 기능을 이용해주세요.";
        } else {
            return "예약된날짜가 캘린더에 등록되었습니다.";
        }
    }
}
