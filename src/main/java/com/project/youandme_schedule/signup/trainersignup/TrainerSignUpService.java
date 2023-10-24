package com.project.youandme_schedule.signup.trainersignup;

import com.project.youandme_schedule.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerSignUpService {

    private final TrainerSignUpRepository trainerSignUpRepository;

    @Autowired
    public TrainerSignUpService(TrainerSignUpRepository trainerSignUpRepository) {
        this.trainerSignUpRepository = trainerSignUpRepository;
    }

    public boolean isIdExist(Long id) {
        Optional<Trainer> existingUser = trainerSignUpRepository.findById(id);
        return existingUser.isEmpty();
    }

    public boolean isEmailExist(String email) {
        Optional<Trainer> existingUser = trainerSignUpRepository.findByEmail(email);
        return existingUser.isEmpty();
    }

    public boolean isPhoneExist(String phone) {
        Optional<Trainer> existingUser = trainerSignUpRepository.findByPhone(phone);
        return existingUser.isEmpty();
    }

//    public Trainer createTrainer(Trainer trainer) {
//        return trainerSignUpRepository.save(trainer);
//    }

}
