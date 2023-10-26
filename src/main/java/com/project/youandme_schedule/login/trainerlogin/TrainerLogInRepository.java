package com.project.youandme_schedule.login.trainerlogin;

import com.project.youandme_schedule.trainer.Trainer;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerLogInRepository extends JpaRepository<Trainer, Long> {
    @NotNull
    Optional<Trainer> findById(Long id);
}
