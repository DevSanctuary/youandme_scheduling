package com.project.youandme_schedule.signup.usersignup;

import com.project.youandme_schedule.user.User;
import com.project.youandme_schedule.verification.smsSender.SmsSender;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserSignUpControllerTest {
    @Mock
    private UserSignUpService userSignUpService;

    @Mock
    private SmsSender smsSender;

    @InjectMocks
    private UserSignUpController userSignUpController;

    @Test
    void testRegisterUserSuccess() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setName("Test User");

        when(userSignUpService.isIdExist(1L)).thenReturn(true);
        when(userSignUpService.isEmailExist("test@example.com")).thenReturn(true);
        when(userSignUpService.isPhoneExist("1234567890")).thenReturn(true);
        when(smsSender.getVerificationCode()).thenReturn("123456");
//        when(smsSender.sendSms(eq("1234567890"), eq("123456"))).thenReturn(true);

        ResponseEntity<String> response = userSignUpController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("회원가입이 완료되었습니다.", response.getBody());
    }

    @Test
    void testRegisterUserWithExistingId() {
        User user = new User();
        user.setId(1L);
        when(userSignUpService.isIdExist(1L)).thenReturn(false);

        ResponseEntity<String> response = userSignUpController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("이미 존재하는 아이디입니다.", response.getBody());
    }

    @Test
    void testRegisterUserWithExistingEmail() {
        User user = new User();
        user.setEmail("existing@example.com");
        when(userSignUpService.isEmailExist("existing@example.com")).thenReturn(false);

        ResponseEntity<String> response = userSignUpController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("이미 가입되어 있는 이메일입니다.", response.getBody());
    }

    @Test
    void testRegisterUserWithExistingPhone() {
        User user = new User();
        user.setPhone("1234567890");
        when(userSignUpService.isPhoneExist("1234567890")).thenReturn(false);

        ResponseEntity<String> response = userSignUpController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("이미 가입되어 있는 전화번호입니다.", response.getBody());
    }

    @Test
    void testRegisterUserWithInvalidPhoneVerification() {
        User user = new User();
        user.setPhone("1234567890");
        when(userSignUpService.isIdExist(1L)).thenReturn(true);
        when(userSignUpService.isEmailExist("test@example.com")).thenReturn(true);
        when(userSignUpService.isPhoneExist("1234567890")).thenReturn(true);
        when(smsSender.getVerificationCode()).thenReturn("123456");
//        when(smsSender.sendSms("1234567890", "123456")).thenReturn(false);

        ResponseEntity<String> response = userSignUpController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("휴대폰 인증에 실패하였습니다.", response.getBody());
    }

}