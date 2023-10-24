package com.project.youandme_schedule.signup.trainersignup;

import com.project.youandme_schedule.trainer.Trainer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerSignUpRepository extends JpaRepository<Trainer, Long> {
    @NonNull
    Optional<Trainer> findById(Long id);
    @NonNull
    Optional<Trainer> findByEmail(String email);
    @NonNull
    Optional<Trainer> findByPhone(String phone);

}
