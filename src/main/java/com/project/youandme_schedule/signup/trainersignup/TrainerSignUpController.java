package com.project.youandme_schedule.signup.trainersignup;

import com.project.youandme_schedule.trainer.Trainer;
import com.project.youandme_schedule.trainer.TrainerRepository;
import com.project.youandme_schedule.verification.smsSender.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerSignUpController {
    @Autowired
    private final TrainerRepository trainerRepository;

    @Autowired
    private final TrainerSignUpService trainerSignUpService;

    @Autowired
    private final SmsSender smsSender;

    @PostMapping("/trainers")
    public ResponseEntity<String> registerUser(@RequestBody Trainer trainer) {

        if (!trainerSignUpService.isIdExist(trainer.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        }

        if (!trainerSignUpService.isEmailExist(trainer.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입되어 있는 이메일입니다.");
        }

        if (!trainerSignUpService.isPhoneExist(trainer.getPhone())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입되어 있는 전화번호입니다.");
        }


        String verificationCode = smsSender.getVerificationCode();
        smsSender.sendSms(trainer.getPhone(), verificationCode);
        boolean isPhoneVerified = performPhoneVerification(verificationCode);

        if (isPhoneVerified) {
            trainer.setName(trainer.getName());
            trainerRepository.save(trainer);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("휴대폰 인증에 실패하였습니다.");
        }

    }

    private boolean performPhoneVerification(String verificationCode) {
        return verificationCode.equals(smsSender.getVerificationCode());
    }

}
