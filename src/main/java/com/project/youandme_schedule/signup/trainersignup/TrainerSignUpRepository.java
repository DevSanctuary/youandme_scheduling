package com.project.youandme_schedule.signup.trainersignup;

import com.project.youandme_schedule.trainer.Trainer;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerSignUpRepository extends JpaRepository<Trainer, Long> {
    @NotNull
    Optional<Trainer> findById(Long id);
    @NotNull
    Optional<Trainer> findByEmail(String email);
    @NotNull
    Optional<Trainer> findByPhone(String phone);

}
