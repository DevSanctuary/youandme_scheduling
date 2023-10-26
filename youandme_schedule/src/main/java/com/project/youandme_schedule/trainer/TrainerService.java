package com.project.youandme_schedule.trainer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    @Transactional
    public Trainer addTrainer(Long trainerId, AddTrainerForm form){
        return trainerRepository.save(Trainer.of(trainerId,form));
    }
}
