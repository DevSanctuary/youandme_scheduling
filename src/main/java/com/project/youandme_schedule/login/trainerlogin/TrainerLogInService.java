package com.project.youandme_schedule.login.trainerlogin;

import com.project.youandme_schedule.securityconfig.PasswordEncoderUtil;
import com.project.youandme_schedule.trainer.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerLogInService {

    private final TrainerLogInRepository trainerLogInRepository;

    @Autowired
    public TrainerLogInService(TrainerLogInRepository trainerLogInRepository) {
        this.trainerLogInRepository = trainerLogInRepository;
    }

    public Optional<Trainer> authenticatedUser(Long id, String password) {
        Optional<Trainer> existingUser = trainerLogInRepository.findById(id);
        if (existingUser.isPresent() && PasswordEncoderUtil.matches(password, existingUser.get().getPassword())) {
            return existingUser;
        }
        return Optional.empty();
    }

}
