package com.project.youandme_schedule.signup.usersignup;

import com.project.youandme_schedule.securityconfig.PasswordEncoderUtil;
import com.project.youandme_schedule.user.User;
import com.project.youandme_schedule.user.UserRepository;
import com.project.youandme_schedule.verification.smsSender.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserSignUpController {
    private final UserRepository userRepository;
    private final UserSignUpService userSignUpService;
    private final SmsSender smsSender;

    @Autowired
    public UserSignUpController(UserRepository userRepository, UserSignUpService userSignUpService, SmsSender smsSender) {
        this.userRepository = userRepository;
        this.userSignUpService = userSignUpService;
        this.smsSender = smsSender;
    }

    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        if (!userSignUpService.isIdExist(user.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        }

        if (!userSignUpService.isEmailExist(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입되어 있는 이메일입니다.");
        }

        if (!userSignUpService.isPhoneExist(user.getPhone())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입되어 있는 전화번호입니다.");
        }


        String verificationCode = smsSender.getVerificationCode();
        smsSender.sendSms(user.getPhone(), verificationCode);
        boolean isPhoneVerified = performPhoneVerification(verificationCode);

        if (isPhoneVerified) {
            String encodedPassword = PasswordEncoderUtil.encodePassword(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);

            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("휴대폰 인증에 실패하였습니다.");
        }

    }

    private boolean performPhoneVerification(String verificationCode) {
        return verificationCode.equals(smsSender.getVerificationCode());
    }

}
