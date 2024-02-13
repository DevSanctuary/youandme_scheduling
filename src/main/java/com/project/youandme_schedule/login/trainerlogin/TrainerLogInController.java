package com.project.youandme_schedule.login.trainerlogin;

import com.project.youandme_schedule.trainer.Trainer;
import com.project.youandme_schedule.trainer.TrainerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/trainers")
public class TrainerLogInController {

    private final TrainerRepository trainerRepository;
    private final TrainerLogInService trainerLogInService;

    @Autowired
    public TrainerLogInController(TrainerRepository trainerRepository, TrainerLogInService trainerLogInService) {
        this.trainerRepository = trainerRepository;
        this.trainerLogInService = trainerLogInService;
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> loginTrainer(@RequestBody LoginRequest loginRequest) {
        Optional<Trainer> authenticatedUser = trainerLogInService.authenticatedUser(loginRequest.getId(), loginRequest.getPassword());

        if (authenticatedUser.isPresent()) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 사용자 또는 패스워드가 일치하지 않습니다.");
        }

    }

    @Data
    public static class LoginRequest {
        private Long id;
        private String password;
    }
}
